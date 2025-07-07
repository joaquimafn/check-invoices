#!/bin/bash

# Script simples para testar o health check da aplicação
# Certifique-se de que a aplicação está rodando em http://localhost:8080

BASE_URL="http://localhost:8080/api"

echo "=== Testando Health Check da Aplicação ==="
echo

# Teste 1: Health Check
echo "1. Testando Health Check..."
curl -s -X GET "$BASE_URL/health" | jq .
echo

# Teste 2: Info Check
echo "2. Testando Info..."
curl -s -X GET "$BASE_URL/health/info" | jq .
echo

echo "=== Testes concluídos! ==="
echo "A aplicação está pronta para desenvolvimento." 