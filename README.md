# Finance API - Controle Financeiro Pessoal

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![H2](https://img.shields.io/badge/H2_Database-1E88E5?style=for-the-badge)

## 📄 Sobre o Projeto

API REST para controle financeiro pessoal com autenticação JWT, desenvolvida em **Spring Boot**. O projeto permite o cadastro de receitas e despesas, com separação clara entre as finanças do usuário.

## 🛠 Tecnologias utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Security + JWT**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **Maven**
- **ModelMapper** / Bean Validation

## 📚 Sobre o desenvolvimento

Este projeto foi desenvolvido durante minha fase de aprendizado em back-end Java.
Ao longo do desenvolvimento, busquei aplicar boas práticas como separação de responsabilidades, arquitetura em camadas e organização do código.

O projeto está em constante evolução e novas melhorias serão implementadas conforme avanço nos estudos.

## 💡 O que aprendi e desafios superados
Durante a criação desse projeto, enfrentei várias etapas e superei, aprimorando meu aprendizado em geral.

- Implementar autenticação via Spring Security foi uma das maiores etapas que enfrentei durante o projeto, pois de início era muito complexo para mim, porém fui estudando seu fluxo e entendendo como ele funciona. E agora, o projeto está com Spring Security implementado com verificações de tokens.

- Gerenciar corretamente relações entre as classes, como o usuário com suas finanças(one-to-many) fazendo com que a arquitetura fique correta entre suas classes.

- Arquitetura em camadas também foi um desafio superado, pois a cada linha de código eu iria adicionar, faria revisar mentalmente se aquilo seria semânticamente correto para a arquitetura, e se não a quebraria e respeitaria sua hierarquia.

## 🚀 Melhorias futuras

* Implementação de filtros de finanças por data, categoria e valor
* Autenticação e autorização com Spring Security ✔️
* Criptografia de senhas utilizando hash ✔️
* Melhorias de performance e segurança ✔️

## 🧱 Arquitetura

O projeto segue **arquitetura em camadas**:

- Controller → Recebe as requisições
- Service → Regras de negócios
- Repository → acesso aos dados
- DTO → transferência de dados
- Entity → modelo do banco

## ⚙️ Pré-requisitos

* Java 21 ou superior

## ▶️ Como executar o projeto

### Clonar o repositório

```bash
git clone https://github.com/MikaelMarquess/Finance-Project-API.git
cd Finance-Project-API
```

### Executar a aplicação

```bash
mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

## 🛣️ Rotas
Com o projeto sendo executado, use algum programa para usar as rotas fornecidas pela API, como o postman.

### Rota para acessar o banco de dados

**URL NAVEGADOR H2DATABASE** ``http://localhost:8080/h2-console``

### Rotas de autenticação
### Registrar usuário


**POST** `http://localhost:8080/authentication/register`

**Body (JSON)**

```json
{  
    "name": "Exemplo nome",
    "email": "exemplo@gmail.com",
    "password": "123456"
}
```
**Response (HEADERS)**

```
http://localhost:8080/authentication/login
```

### Login usuário

**POST** `http://localhost:8080/authentication/login`

**Body (JSON)**

```json
{
    "email": "exemplo@gmail.com",
    "password": "123456"
}
```

**Response (JSON)**
```json
{ 
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6Im1pazIxMjFAZ21haWwuY29tIiwiZXhwIjoxNzc0ODc3MDQ3fQ.vjWEteQObMXgpq1lwcZnk7G-tBucfRNQZ2J9GU1Eq1M"
}
```

**🔐AUTORIZAÇÃO**

Após realizar o login com sucesso, será retornado um token JWT. Esse token deve ser utilizado para acessar rotas protegidas da API.

Para isso, adicione o token no header da requisição da seguinte forma:

``Authorization: Bearer Token SEU_TOKEN_AQUI``


### Rotas do usuário

### Todas finanças do usuário

**GET** `http://localhost:8080/users/finances`

**Response (JSON)**
```json
{
   "revenues": [],
    "expenses": [],
    "totalRevenue": 0.0,
    "totalExpense": 0.0,
    "finalRecipe": 0.0
}
```

### Atualizar dados do usuário

**PUT** `http://localhost:8080/users/updateData`

**Body (JSON)**
```json
{
    "name": "Exemplo nome",
    "email": "exemplo@gmail.com",
    "password": "123456"
}
```

É apenas necessário preencher os dados que você deseja atualizar.

**Response (Headers)**
```
http://localhost:8080/authentication/login
```
Após atualizar os dados, é necessário fazer login novamente.

### Deletar dados

**DELETE** `http://localhost:8080/users/delete`

**Body (JSON)**
```json
{
    "email": "mik2121@gmail.com",
    "password": "123456"
}
```

**Response (Status)**

```
200 OK
```

### Rotas de receitas do usuário

### Criar uma nova receita

**POST** `http://localhost:8080/revenues/create`

**Body (JSON)**
````json
{
    "financeValue": 1800.00,
    "description": "Descrição exemplo",
    "date": "2026-02-06",
    "category": "SALARY"
}
````
**CATEGORIAS DISPONIVEIS NO MOMENTO:** ``SALARY, EXTRA, INVESTMENTS``

**Response (JSON)**
````json
{
    "category": "SALARY",
    "date": "2026-02-06",
    "description": "Descrição exemplo",
    "financeValue": 1800.0,
    "id": 5
}
````

**Atualizar dados da receita**

**PUT** `http://localhost:8080/revenues/update/troque-pelo-id-receita`

**Body (JSON)**
````json
{
    "financeValue": 1800.00,
    "description": "Descrição exemplo 2",
    "date": "2026-02-06",
    "category": "EXTRA"
}
````

**Response (JSON)**
````json
{
    "category": "EXTRA",
    "date": "2026-02-06",
    "description": "Descrição exemplo 2",
    "financeValue": 1800.0,
    "id": 5
}
````

**Deletar a receita**

**DELETE** `http://localhost:8080/revenues/delete/troque-pelo-id-receita`

**Response (STATUS)**

```
200 OK
```

### Rotas de despesas do usuário

### Criar uma nova despesa

**POST** `http://localhost:8080/expenses/create`

**Body (JSON)**

````json
{
    "financeValue": 500,
    "description": "descrição exemplo",
    "date": "2025-12-29",
    "category": "HEALTH"
}
````
**CATEGORIAS DISPONÍVEIS NO MOMENTO:** ``FOOD, HOUSING, TRANSPORTATION, LEISURE, HEALTH``

**Response (JSON)**
````json
{
    "category": "HEALTH",
    "date": "2025-12-29",
    "description": "descrição exemplo",
    "financeValue": 500.0,
    "id": 11
}
````

### Atualizar dados da despesa

**PUT** `http://localhost:8080/expenses/update/troque-pelo-id-despesa`

**Body (JSON)**
````json
{
    "financeValue": 300,
    "description": "descricao exemplo 123",
    "date": "2026-01-01",
    "category": "LEISURE"
}
````

**Response (JSON)**
````json
{
    "category": "LEISURE",
    "date": "2026-01-01",
    "description": "descricao exemplo 123",
    "financeValue": 300.0,
    "id": 11
}
````

### Deletar uma despesa

**DELETE** `http://localhost:8080/expenses/delete/troque-pelo-id-despesa`

**Response (STATUS)**
````
200 OK
````








