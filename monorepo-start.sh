#!/bin/bash

# Cores para saída
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}=== Iniciando Monorepo Infuse Tecnologia ===${NC}"

# Verificar parâmetro do ambiente
ENV=${1:-prod}

if [ "$ENV" == "prod" ] || [ "$ENV" == "production" ]; then
    PROFILE="prod"
    echo -e "${YELLOW}Iniciando em ambiente de PRODUÇÃO${NC}"
else
    PROFILE="dev"
    echo -e "${YELLOW}Iniciando em ambiente de DESENVOLVIMENTO${NC}"
fi

# Iniciar os serviços com Docker Compose
echo -e "${GREEN}Iniciando serviços com Docker Compose...${NC}"
docker compose --profile $PROFILE up --build -d

echo -e "\n${GREEN}Serviços iniciados com sucesso!${NC}"

# Listar contêineres em execução
echo -e "\n${YELLOW}--- Contêineres em Execução ---${NC}"
docker compose ps

# Exibir informações de acesso
echo -e "\n${YELLOW}--- Acesso aos Serviços ---${NC}"
echo -e "Backend API: ${GREEN}http://localhost:8080${NC}"
echo -e "Documentação da API (Swagger UI): ${GREEN}http://localhost:8080/swagger-ui.html${NC}"

if [ "$PROFILE" == "prod" ]; then
    echo -e "Frontend: ${GREEN}http://localhost:3000${NC}"
    echo -e "PostgreSQL: ${GREEN}localhost:5432${NC}"
else
    echo -e "Frontend: ${GREEN}http://localhost:4200${NC}"
fi

echo -e "Kafka: ${GREEN}localhost:9092${NC}"
echo -e "Zookeeper: ${GREEN}localhost:2181${NC}" 