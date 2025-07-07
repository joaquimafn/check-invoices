#!/bin/bash

# Cores para output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}=== Formatador de código Infuse ===${NC}"
echo

echo -e "${YELLOW}Removendo imports não utilizados e formatando código...${NC}"
mvn spotless:apply

echo
echo -e "${YELLOW}Verificando conformidade com os padrões de código...${NC}"
mvn checkstyle:check

echo
echo -e "${GREEN}Processo concluído!${NC}" 