# Plataforma Pública de Consulta de Créditos — INFUSE TECNOLOGIA

Imagine um cenário real: auditores fiscais, contadores e cidadãos precisam consultar créditos constituídos de forma simples, transparente e auditável. Este projeto simula uma plataforma pública de consulta de créditos, servindo como backend de um portal web e, futuramente, de uma aplicação mobile.

A solução foi desenhada para ser robusta, extensível e pronta para crescer junto com as demandas do negócio.

---

## 🚀 Visão Geral do Produto

- **Propósito:** Facilitar a consulta de créditos por NFS-e ou número de crédito, promovendo transparência e automação para órgãos públicos e cidadãos.
- **Público-alvo:** Auditores fiscais, contadores, cidadãos e integradores de sistemas.
- **Futuro:** API pública, integração mobile, exportação de relatórios e autenticação avançada.

---

## 🏗️ Estrutura do Monorepo

```
.
├── packages/
│   ├── backend/         # API Spring Boot (Java 17)
│   └── frontend/        # Frontend Angular
├── docker-compose.yml   # Orquestração local (backend, frontend, Kafka, DB)
├── monorepo-start.sh    # Script para subir todo o ambiente de uma vez
└── k8s/                 # Manifests para deploy em Kubernetes
```

---

## 🛠️ Como rodar localmente

### Pré-requisitos

- JDK 17, Maven, Node.js, Docker e Docker Compose

### Subindo tudo com Docker Compose

```bash
docker-compose up --build
```

### Subindo tudo com script (recomendado)

O script `monorepo-start.sh` automatiza o processo de subir backend, frontend e dependências. Basta rodar:

```bash
./monorepo-start.sh
```

### Backend (Java)

```bash
cd packages/backend
./mvnw spring-boot:run
```

### Frontend (Angular)

```bash
cd packages/frontend
npm install
npm start
```

### API Docs

- [Swagger UI](http://localhost:8080/swagger-ui.html)

---

## 📐 Decisões de Engenharia

- **DTOs isolam entidades JPA da API:** Evita vazamento de detalhes internos e facilita evolução do modelo.
- **Kafka desacoplado via service especializado:** O controller só dispara eventos, sem saber detalhes de mensageria.
- **Testes cobrindo sucesso e erro:** Mock no serviço, integração no controller, cobrindo cenários reais.
- **Docker Compose para onboarding:** Suba tudo com um comando, sem dor de cabeça para novos devs.
- **MapStruct para mapeamento:** Reduz boilerplate e garante consistência entre entidades e DTOs.
- **OpenAPI/Swagger:** Documentação viva e interativa para devs e stakeholders.

---

### 🧠 Por que optei por Kafka e não outro mecanismo?

- Kafka é tolerante a falhas e distribuído por natureza.
- Integração fácil com microserviços e consumidores diversos.
- Foco em eventos imutáveis facilita rastreamento e auditoria.
- Escalabilidade horizontal nativa.

**Alternativas consideradas:** Azure Service Bus, Redis Streams, RabbitMQ.

---

## 🗂️ Mini Wiki Técnica

### Estrutura dos Pacotes (Backend)

- `controller/` — Endpoints REST (ex: `CreditoController`)
- `service/` — Regras de negócio e integração (ex: `CreditService`, `KafkaProducerService`)
- `model/` — Entidades JPA (ex: `Credit`)
- `dto/` — Data Transfer Objects (ex: `CreditDTO`, `ConsultaCreditoEventoDTO`)
- `repository/` — Interfaces JPA (ex: `CreditRepository`)
- `config/` — Configurações (Kafka, DB, Swagger, CORS)
- `exception/` — Tratamento global de erros
- `mapper/` — Conversão entre entidades e DTOs (MapStruct)

### Como estender a API com novos endpoints?

1. Crie um novo DTO em `dto/` se necessário.
2. Implemente a lógica de negócio em um novo ou existente `service/`.
3. Adicione o endpoint no controller correspondente.
4. Documente com Swagger/OpenAPI.
5. Teste com mocks e integração.

### Integração Kafka

- Configuração em `config/KafkaProducerConfig.java` e `KafkaTopicConfig.java`.
- Publicação de eventos via `KafkaProducerService`.
- Tópico configurável via `application.properties`.

---

## 🧪 Testes

- **Unitários:** Mock de serviços e repositórios.
- **Integração:** Testes de controller com MockMvc.
- **Scripts de health-check:** Validação rápida do ambiente.

---

## 📈 Roadmap do Produto

- **v1.1:** Autenticação JWT com perfil de auditor
- **v1.2:** Exportação de relatórios em CSV e PDF
- **v1.3:** Integração com sistema legado via SOAP adapter
- **v2.0:** Consulta via API pública com rate-limiting

---

## 🤝 Contribuindo

Sinta-se à vontade para abrir issues, sugerir melhorias ou enviar PRs. O objetivo é evoluir a plataforma para cenários reais de negócio.

---

## 📘 Mini Wiki e Documentação

Para detalhes avançados, consulte a [Wiki do projeto](./docs) ou a pasta `/docs` (em construção). 