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
echo "1. Executar com Docker Compose (Recomendado)"
echo "2. Executar apenas PostgreSQL com Docker"
echo "3. Compilar e executar localmente"
echo "4. Testar Health Check da aplicação"
echo "5. Limpar containers Docker"
echo "0. Sair"
echo

read -p "Escolha uma opção [0-5]: " choice

case $choice in
    1)
        print_info "Executando com Docker Compose..."
        docker-compose up --build
        ;;
    2)
        print_info "Executando apenas PostgreSQL com Docker..."
        docker run --name infuse-postgres -e POSTGRES_DB=infuse_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15
        print_info "PostgreSQL iniciado. Conexão: jdbc:postgresql://localhost:5432/infuse_db"
        print_info "Para executar a aplicação localmente, use: mvn spring-boot:run"
        ;;
    3)
        print_info "Compilando e executando localmente..."
        if command -v mvn &> /dev/null; then
            mvn clean install
            mvn spring-boot:run
        else
            print_error "Maven não está instalado. Instale o Maven para continuar."
        fi
        ;;
    4)
        print_info "Testando Health Check..."
        if [ -f "health-check.sh" ]; then
            chmod +x health-check.sh
            ./health-check.sh
        else
            print_error "Arquivo health-check.sh não encontrado."
        fi
        ;;
    5)
        print_info "Limpando containers Docker..."
        docker-compose down -v
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