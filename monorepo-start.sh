#!/bin/bash

# Cores para saída
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}=== Iniciando Monorepo Infuse Tecnologia ===${NC}"

# Verificar parâmetro do ambiente
ENV=${1:-dev}

if [ "$ENV" == "prod" ] || [ "$ENV" == "production" ]; then
    PROFILE="prod"
    echo -e "${YELLOW}Iniciando em ambiente de PRODUÇÃO${NC}"
else
    PROFILE="dev"
    echo -e "${YELLOW}Iniciando em ambiente de DESENVOLVIMENTO${NC}"
fi

# Iniciar os serviços com Docker Compose
echo -e "${GREEN}Iniciando serviços com Docker Compose...${NC}"
docker-compose --profile $PROFILE up -d

echo -e "${GREEN}Serviços iniciados com sucesso!${NC}"
echo -e "${GREEN}Backend: http://localhost:8080${NC}"
if [ "$PROFILE" == "prod" ]; then
    echo -e "${GREEN}Frontend: http://localhost:3000${NC}"
else
    echo -e "${GREEN}Frontend: http://localhost:4200${NC}"
fi
echo -e "${GREEN}Swagger UI: http://localhost:8080/swagger-ui.html${NC}" 