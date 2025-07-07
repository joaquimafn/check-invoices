# Infuse Tecnologia - Monorepo

Este repositório contém os projetos de backend e frontend da Infuse Tecnologia organizados em uma estrutura de monorepo.

## Estrutura do Projeto

```
.
├── packages/
│   ├── backend/         # Aplicação Spring Boot
│   │   ├── src/         # Código fonte do backend
│   │   └── ...
│   └── frontend/        # Aplicação frontend
│       └── ...          # (a ser implementada)
├── package.json         # Configuração do monorepo
└── README.md            # Este arquivo
```

## Requisitos

- JDK 17
- Maven
- Node.js
- Docker e Docker Compose (para ambiente de produção)

## Como Iniciar

### Backend

#### Ambiente de Desenvolvimento

```bash
npm run backend:dev
# ou
cd packages/backend && ./dev-setup.sh
```

#### Ambiente de Produção

```bash
npm run backend:start
# ou
cd packages/backend && ./docker-start.sh
```

### Frontend

#### Ambiente de Desenvolvimento

```bash
# Primeiro, instale as dependências
cd packages/frontend && npm install

# Inicie o servidor de desenvolvimento
npm run frontend:dev
# ou
cd packages/frontend && npm run dev
```

#### Build para Produção

```bash
npm run frontend:build
# ou
cd packages/frontend && npm run build
```

## API Documentation

A documentação da API REST está disponível em:

- Desenvolvimento: http://localhost:8080/swagger-ui.html
- Produção: [URL de produção]/swagger-ui.html 