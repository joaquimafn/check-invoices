# Infuse Tecnologia - Backend

Este é o backend do projeto Infuse, desenvolvido com Spring Boot.

## Configuração e Execução

### Requisitos
- JDK 17 ou superior
- Maven
- Docker e Docker Compose (para ambiente de produção)
- Node.js e npm (para os hooks de git)

### Executando em Ambiente de Desenvolvimento
```bash
./dev-setup.sh
```

### Executando em Ambiente de Produção
```bash
./docker-start.sh prod
```

## Qualidade de Código

### Ferramentas Utilizadas
- Checkstyle: Para verificação de estilo de código
- Spotless: Para formatação automática de código
- Husky: Para git hooks

### Como Usar

#### Formatar Código Manualmente
Para formatar o código manualmente:
```bash
./format-code.sh
```

Ou pela raiz do monorepo:
```bash
npm run format:backend
```

#### Verificar Estilo de Código
Para verificar se o código segue os padrões configurados:
```bash
mvn checkstyle:check
```

#### Hooks de Git
Os hooks de git são executados automaticamente quando você faz um commit. Eles garantem que:

1. O código seja formatado automaticamente
2. As regras de estilo sejam verificadas

### Configuração Inicial
Para configurar todas as dependências e os git hooks, execute na raiz do monorepo:
```bash
npm run setup
```

## Arquitetura

### Estrutura de Pastas
```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── infuse/
│   │           ├── config/      # Configurações
│   │           ├── controller/  # Controllers REST
│   │           ├── dto/         # Data Transfer Objects
│   │           ├── exception/   # Exceções customizadas
│   │           ├── mapper/      # Mapeadores entre entidades e DTOs
│   │           ├── model/       # Entidades
│   │           ├── repository/  # Repositórios JPA
│   │           └── service/     # Serviços
│   └── resources/
│       ├── application.properties  # Configurações gerais
│       ├── application-dev.properties  # Configurações de desenvolvimento
│       └── data.sql  # Dados iniciais
``` 