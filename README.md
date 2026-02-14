# Health Core API

API REST (Spring Boot) responsável por **intermediar a comunicação entre o aplicativo do cidadão e a plataforma de gerenciamento de filas do SUS**, permitindo:

- Emissão de senha de atendimento
- Consulta de status da senha
- Consulta da ocupação das unidades de saúde
- Autenticação de usuários via JWT

Esta API atua como **camada de acesso** ao sistema distribuído, abstraindo a comunicação com os microsserviços responsáveis pelo processamento dos eventos e cálculo de métricas operacionais.

---

## Principais Features

- Geração remota de senha de atendimento
- Consulta de status da senha emitida
- Consulta da ocupação de unidades de saúde
- Autenticação e autorização via JWT
- API stateless baseada em tokens
- Integração com serviços de mensageria (via broker)

---

---

## Variáveis de Ambiente

Configurações podem ser sobrescritas via `.env`.

Exemplo:

SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/healthcoredb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

---

## Build da Aplicação

mvn clean package -DskipTests

---

## Executar Aplicação

java -jar target/*.jar

## Endpoints

### Health Check

Verifica se a API está disponível.

#### Request

GET /apihealthcheck

#### Response

200 OK

The API is running and available

---

### Gerar Senha de Atendimento

Permite que o usuário gere remotamente uma senha de atendimento.

#### Request

POST /user/ticket

Body:

"user-id-123"

#### Response

201 Created

{
"ticketId": "abc123",
"status": "ACTIVE",
"generatedAt": "2026-02-13T12:34:56Z"
}

---

### Consultar Status da Senha

Retorna o status atual da senha do usuário.

#### Request

GET /user/ticket

Body:

"ticket-id-123"

#### Response

Id: ticket-id-123, Ticket Status: Valid

---

### Consultar Ocupação das Unidades

Permite verificar o nível de ocupação das unidades próximas.

#### Request

GET /user/ticket/occupancy

Body:

"region-01"

#### Response

EXAMPLE: Unit1: 70%, Unit2: 50%

---

## Autenticação

### Criar Novo Usuário

POST /auth/addNewUser

Body:

{
"name": "Julio",
"email": "julio@email.com",
"password": "123456",
"roles": "ROLE_USER"
}

---

### Gerar Token JWT

POST /auth/generateToken

Body:

{
"username": "julio@email.com",
"password": "123456"
}

#### Response

"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."

---

### Endpoint Protegido

GET /auth/user/userdata

Necessário enviar o token JWT no header:

Authorization: Bearer <TOKEN>

---

## Arquitetura (Visão Simplificada)

Mobile App
|
v
Health Core API
|
v
Event Broker
|
v
Queue Consumer Service
|
+--> PostgreSQL
|
+--> Redis

A API é responsável por receber requisições do cliente e encaminhar solicitações ao sistema de filas distribuído, consultando posteriormente o estado do atendimento e métricas operacionais armazenadas em cache.

---

## Banco de Dados

A API utiliza PostgreSQL como camada de persistência para:

- Cadastro de usuários
- Controle de autenticação
- Informações de autorização

---

## Docker

### Subir os containers

docker compose up --build

Serviços:

| Serviço     | Porta |
|------------|------|
| API        | 8085 |
| PostgreSQL | 5432 |


