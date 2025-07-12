#!/bin/bash

# Script para facilitar o setup de desenvolvimento
# Autor: Infuse Tecnologia

echo "=== Setup de Desenvolvimento - Infuse Spring Boot ==="
echo

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Função para imprimir com cores
print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Encontra o caminho para o docker-compose.yml
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MONOREPO_ROOT="$(dirname "$(dirname "$SCRIPT_DIR")")"
DOCKER_COMPOSE_FILE="$MONOREPO_ROOT/docker-compose.yml"

# Verificar se Docker está instalado
if ! command -v docker &> /dev/null; then
    print_error "Docker não está instalado. Instale o Docker para continuar."
    exit 1
fi

# Verificar se Docker Compose está instalado
if ! command -v docker-compose &> /dev/null; then
    print_error "Docker Compose não está instalado. Instale o Docker Compose para continuar."
    exit 1
fi

# Verificar se Maven está instalado
if ! command -v mvn &> /dev/null; then
    print_warn "Maven não está instalado. Será necessário para compilação local."
fi

# Verificar se Java 17 está instalado
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
    if [ "$JAVA_VERSION" -ge "17" ]; then
        print_info "Java $JAVA_VERSION encontrado"
    else
        print_warn "Java 17 ou superior é recomendado. Versão atual: $JAVA_VERSION"
    fi
else
    print_warn "Java não está instalado. Será necessário para compilação local."
fi

echo
echo "=== Opções de Execução ==="
echo "1. Executar com Docker Compose (Ambiente de Desenvolvimento)"
echo "2. Executar com Docker Compose (Ambiente de Produção)"
echo "3. Executar apenas PostgreSQL com Docker"
echo "4. Compilar e executar localmente"
echo "5. Testar Health Check da aplicação"
echo "6. Limpar containers Docker"
echo "0. Sair"
echo

read -p "Escolha uma opção [0-6]: " choice

case $choice in
    1)
        print_info "Executando com Docker Compose em ambiente de desenvolvimento..."
        if [ -f "$SCRIPT_DIR/docker-start.sh" ]; then
            chmod +x "$SCRIPT_DIR/docker-start.sh"
            "$SCRIPT_DIR/docker-start.sh" dev
        else
            print_error "Arquivo docker-start.sh não encontrado."
        fi
        ;;
    2)
        print_info "Executando com Docker Compose em ambiente de produção..."
        if [ -f "$SCRIPT_DIR/docker-start.sh" ]; then
            chmod +x "$SCRIPT_DIR/docker-start.sh"
            "$SCRIPT_DIR/docker-start.sh" prod
        else
            print_error "Arquivo docker-start.sh não encontrado."
        fi
        ;;
    3)
        print_info "Executando apenas PostgreSQL com Docker..."
        docker run --name infuse-postgres -e POSTGRES_DB=infuse_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15
        print_info "PostgreSQL iniciado. Conexão: jdbc:postgresql://localhost:5432/infuse_db"
        print_info "Para executar a aplicação localmente, use: mvn spring-boot:run"
        ;;
    4)
        print_info "Compilando e executando localmente..."
        if command -v mvn &> /dev/null; then
            mvn clean install -DskipTests -Dcheckstyle.skip=true -Dspotless.skip=true -Dfmt.skip=true
            mvn spring-boot:run
        else
            print_error "Maven não está instalado. Instale o Maven para continuar."
        fi
        ;;
    5)
        print_info "Testando Health Check..."
        if [ -f "$SCRIPT_DIR/health-check.sh" ]; then
            chmod +x "$SCRIPT_DIR/health-check.sh"
            "$SCRIPT_DIR/health-check.sh"
        else
            print_error "Arquivo health-check.sh não encontrado."
        fi
        ;;
    6)
        print_info "Limpando containers Docker..."
        if [ -f "$DOCKER_COMPOSE_FILE" ]; then
            docker-compose -f "$DOCKER_COMPOSE_FILE" down -v
        else
            print_warn "Arquivo docker-compose.yml não encontrado. Executando limpeza padrão."
            docker-compose down -v 2>/dev/null || true
        fi
        docker system prune -f
        print_info "Containers removidos."
        ;;
    0)
        print_info "Saindo..."
        exit 0
        ;;
    *)
        print_error "Opção inválida."
        exit 1
        ;;
esac 