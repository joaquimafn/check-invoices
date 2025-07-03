#!/bin/bash

# Docker start script for Infuse Application
# Usage: ./docker-start.sh [dev|prod]

PROFILE=${1:-dev}

case $PROFILE in
    dev|development)
        echo "🚀 Starting application in DEVELOPMENT mode with SQLite..."
        echo "Database: SQLite (file: ./dev-database.db)"
        echo "Port: 8080"
        echo "Profile: dev"
        docker-compose --profile dev up --build
        ;;
    prod|production)
        echo "🚀 Starting application in PRODUCTION mode with PostgreSQL..."
        echo "Database: PostgreSQL (localhost:5432)"
        echo "Port: 8080"
        echo "Profile: prod"
        docker-compose --profile prod up --build
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