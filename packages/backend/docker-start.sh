#!/bin/bash

# Docker start script for Infuse Application
# Usage: ./docker-start.sh [dev|prod]

PROFILE=${1:-dev}

# Encontra o caminho para o docker-compose.yml
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
MONOREPO_ROOT="$(dirname "$(dirname "$SCRIPT_DIR")")"
DOCKER_COMPOSE_FILE="$MONOREPO_ROOT/docker-compose.yml"

# Verifica se o arquivo docker-compose.yml existe
if [ ! -f "$DOCKER_COMPOSE_FILE" ]; then
    echo "❌ Arquivo docker-compose.yml não encontrado em: $DOCKER_COMPOSE_FILE"
    exit 1
fi

case $PROFILE in
    dev|development)
        echo "🚀 Starting application in DEVELOPMENT mode with SQLite..."
        echo "Database: SQLite (file: ./dev-database.db)"
        echo "Port: 8080"
        echo "Profile: dev"
        docker-compose -f "$DOCKER_COMPOSE_FILE" --profile dev up --build
        ;;
    prod|production)
        echo "🚀 Starting application in PRODUCTION mode with PostgreSQL..."
        echo "Database: PostgreSQL (localhost:5432)"
        echo "Port: 8080"
        echo "Profile: prod"
        docker-compose -f "$DOCKER_COMPOSE_FILE" --profile prod up --build
        ;;
    *)
        echo "❌ Invalid profile: $PROFILE"
        echo "Usage: $0 [dev|prod]"
        echo ""
        echo "Available profiles:"
        echo "  dev  - Development mode with SQLite"
        echo "  prod - Production mode with PostgreSQL"
        exit 1
        ;;
esac 