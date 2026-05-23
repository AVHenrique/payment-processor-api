# Payment Processor API

Uma API REST para processamento de pagamentos desenvolvida com Java, Spring Boot e PostgreSQL. O projeto demonstra boas práticas de desenvolvimento backend, arquitetura em camadas e integração com bancos de dados.

## 🚀 Stack Tecnológico

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.14** - Framework web
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL 15** - Banco de dados relacional
- **Docker** - Containerização
- **Maven** - Gerenciador de dependências
- **Lombok** - Redução de boilerplate

## 📋 Pré-requisitos

- Java 21+
- Docker e Docker Compose
- Maven 3.6+
- Git

## 🔧 Como Rodar Localmente

### 1. Clone o repositório

```bash
git clone https://github.com/AVHenrique/payment-processor-api.git
cd payment-processor-api
```

### 2. Inicie o PostgreSQL

```bash
docker-compose up -d
```

### 3. Execute a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## 📡 Endpoints da API

### Criar Pagamento

**POST** `/api/payments`

```bash
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "externalId": "PAY-001",
    "amount": 100.00,
    "currency": "BRL",
    "payerEmail": "payer@example.com",
    "payeeEmail": "payee@example.com"
  }'
```

**Response:**
```json
{
  "id": 1,
  "externalId": "PAY-001",
  "amount": 100.00,
  "currency": "BRL",
  "status": "PENDING",
  "payerEmail": "payer@example.com",
  "payeeEmail": "payee@example.com",
  "createdAt": "2026-05-23T19:25:23",
  "updatedAt": "2026-05-23T19:25:23"
}
```

### Buscar Pagamento por ID

**GET** `/api/payments/{id}`

```bash
curl -X GET http://localhost:8080/api/payments/1
```

### Buscar Pagamento por External ID

**GET** `/api/payments/external/{externalId}`

```bash
curl -X GET http://localhost:8080/api/payments/external/PAY-001
```

### Atualizar Status do Pagamento

**PUT** `/api/payments/{id}/status?status=COMPLETED`

```bash
curl -X PUT http://localhost:8080/api/payments/1/status?status=COMPLETED
```

## 📁 Estrutura do Projeto

src/main/java/com/avhenrique/payment_processor/
├── controller/       # Endpoints REST
├── service/          # Lógica de negócio
├── repository/       # Acesso ao banco de dados
├── entity/           # Modelos JPA
├── dto/              # Data Transfer Objects
└── PaymentProcessorApplication.java

## 🗄️ Banco de Dados

### Tabela: payments

| Coluna | Tipo | Descrição |
|--------|------|-----------|
| id | BIGINT | ID primária (auto-increment) |
| external_id | VARCHAR(255) | ID externo único |
| amount | DECIMAL(19,2) | Valor do pagamento |
| currency | VARCHAR(3) | Código da moeda (ex: BRL, USD) |
| status | VARCHAR(50) | Status: PENDING, PROCESSING, COMPLETED, FAILED |
| payer_email | VARCHAR(255) | Email do pagador |
| payee_email | VARCHAR(255) | Email do recebedor |
| created_at | TIMESTAMP | Data de criação |
| updated_at | TIMESTAMP | Data da última atualização |

## 🎯 Funcionalidades

- ✅ CRUD completo de pagamentos
- ✅ Validação de entrada com Jakarta Validation
- ✅ Mapeamento automático entre entidades e DTOs
- ✅ Persistência em PostgreSQL com JPA/Hibernate
- ✅ Tratamento de erros estruturado
- ✅ API RESTful com status HTTP apropriados

## 🚧 Melhorias Futuras

- [ ] Integração com Kafka para processamento assíncrono
- [ ] Autenticação e autorização com JWT
- [ ] Testes unitários e integração
- [ ] Cache com Redis
- [ ] Documentação com Swagger/OpenAPI
- [ ] CI/CD com GitHub Actions
- [ ] Rate limiting
- [ ] Logging estruturado com ELK

## 📝 Licença

Este projeto é de código aberto sob a licença MIT.

## 👤 Autor

**Henrique Vicentini**
- GitHub: [@AVHenrique](https://github.com/AVHenrique)
- LinkedIn: [avhenrique](https://linkedin.com/in/avhenrique)
