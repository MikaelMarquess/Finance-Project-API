# API de Finanças

## 🛠 Tecnologias utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge\&logo=openjdk\&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge\&logo=spring\&logoColor=white)
![H2](https://img.shields.io/badge/H2_Database-1E88E5?style=for-the-badge)

## 📄 Descrição

API REST desenvolvida em Java com Spring Boot, criada com foco em portfólio e aprendizado.
A aplicação permite que o usuário se cadastre, realize login e gerencie suas receitas e despesas, seguindo uma arquitetura em camadas.

## 📚 Sobre o desenvolvimento

Este projeto foi desenvolvido durante minha fase de aprendizado em back-end Java.
Ao longo do desenvolvimento, busquei aplicar boas práticas como separação de responsabilidades, arquitetura em camadas e organização do código.

O projeto está em constante evolução e novas melhorias serão implementadas conforme avanço nos estudos.

## 🚀 Melhorias futuras

* Implementação de filtros de finanças por data, categoria e valor
* Autenticação e autorização com Spring Security e OAuth2
* Criptografia de senhas utilizando hash
* Melhorias de performance e segurança

## 🧱 Arquitetura

O projeto segue arquitetura em camadas:

- Controller
- Service
- Repository
- DTO
- Entity

## ⚙️ Pré-requisitos

* Java 21+

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
### Rotas de autenticação
### Registrar usuário


**POST** `http://localhost:8080/authentication/register`

**Body (JSON)**

```json
{  
    "id": null, //id será criado automaticamente após registro, permaneça com o valor null.
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
    "id": 4, //id do autor
    "name": "Ronaldo"
}
```

### Rotas do usuário

### Todas finanças do usuário

**GET** `http://localhost:8080/users/troque-pelo-id/finances`

**Response (JSON)**
```json
{
    "authorId": 1,
    "revenues": [
        {
            "category": "SALARY",
            "date": "2026-02-04",
            "description": "Salário do mês",
            "financeValue": 4500.0,
            "id": 1,
            "user": {
                "id": 1,
                "name": "Josias"
            }
        }
    ],
    "expenses": [
        {
            "category": "HOUSING",
            "date": "2026-02-04",
            "description": "Aluguel",
            "financeValue": 800.0,
            "id": 1,
            "user": {
                "id": 1,
                "name": "Josias"
            }
        }
    ],
    "totalRevenue": 4500.0,
    "totalDispense": 800.0,
    "finalRecipe": 3700.0
}
```

### Atualizar dados do usuário

**PUT** `http://localhost:8080/users/troque-pelo-id-autor/updateData`

**Body (JSON)**
```json
{
    "id": 1, //troque pelo seu id
    "name": "Exemplo nome",
    "email": "exemplo@gmail.com",
    "password": "123456"
}
```

**Response (Headers)**
```
http://localhost:8080/authentication/login
```
Após atualizar os dados, é necessário fazer login novamente. Isso foi planejado para futuramente implementar jwt token.

### Deletar dados

**DELETE** `http://localhost:8080/users/troque-pelo-id-autor/delete`

**Body (JSON)**
```json
{
    "id": 1, //troque pelo seu id do autor
    "email": "exemplo@gmail.com",
    "password": "123456"
}
```

**Response (status)**

```
200 OK
```

### Rotas de receitas do usuário

### Criar uma nova receita

**POST** `http://localhost:8080/revenues/troque-pelo-id-autor`

**Body (JSON)**
````json
{
    "id": null, //id será criado automaticamente após criar a receita, permaneça com o valor null.
    "financeValue": 1800.00,
    "description": "Descrição exemplo",
    "date": "2026-02-06",
    "category": "SALARY", //categorias disponíveis no momento: SALARY, EXTRA, INVESTMENTS
    "user": {
        "id": 1, //troque pelo seu id do autor
        "name": "Josias"
    }
}
````

**Response (JSON)**
````json
{
    "category": "SALARY",
    "date": "2026-02-06",
    "description": "Descrição exemplo",
    "financeValue": 1800.0,
    "id": 5, //id da receita
    "user": {
        "id": 1, // id do usuário
        "name": "Josias"
    }
}
````

**Atualizar dados da receita**

**PUT** `http://localhost:8080/revenues/update/troque-pelo-id-receita`

**Body (JSON)**
````json
{
    "id": 5, //troque pelo id da sua receita
    "financeValue": 2500.00,
    "description": "Descrição exemplo 2",
    "date": "2026-02-08",
    "category": "EXTRA", //categorias disponíveis no momento: SALARY, EXTRA, INVESTMENTS
    "user": {
        "id": 1, //troque pelo seu id do autor
        "name": "Josias"
    }
}
````

**Response (JSON)**
````json
{
    "category": "EXTRA",
    "date": "2026-02-08",
    "description": "Descrição exemplo 2",
    "financeValue": 2500.0,
    "id": 5, //id da receita
    "user": {
        "id": 1, //id do autor
        "name": "Josias"
    }
}
````

**Deletar a receita**

**DELETE** `http://localhost:8080/revenues/troque-pelo-id-autor/delete/troque-pelo-id-receita`

**Response (STATUS)**

```
200 OK
```

### Rotas de despesas do usuário

### Criar uma nova despesa

**POST** `http://localhost:8080/expenses/troque-pelo-id-autor`

**Body (JSON)**

````json
{
    "id": null, //id será criado automaticamente após criar a receita, permaneça com o valor null.
    "financeValue": 500.00,
    "description": "Descrição exemplo 2",
    "date": "2025-12-29",
    "category": "HEALTH", //categorias disponíveis no momento: FOOD, HOUSING, TRANSPORTATION, LEISURE, HEALTH
    "user": {
        "id": 1, //troque pelo seu id do autor
        "name": "Josias"
    }
}
````

**Response (JSON)**
````json
{
    "category": "HEALTH",
    "date": "2025-12-29",
    "description": "Descrição exemplo 2",
    "financeValue": 500.0,
    "id": 11, //id da despesa
    "user": {
        "id": 1, //id do autor
        "name": "Josias"
    }
}
````

### Atualizar dados da despesa

**PUT** `http://localhost:8080/expenses/update/troque-pelo-id-despesa`

**Body (JSON)**
````json
{
    "id": 11, //troque pelo id da despesa
    "financeValue": 500.00,
    "description": "Descrição exemplo 3",
    "date": "2025-12-30",
    "category": "LEISURE",
    "user": {
        "id": 1, //troque pelo seu id do autor
        "name": "Josias"
    }
}
````

**Response (JSON)**
````json
{
    "category": "HEALTH",
    "date": "2025-12-29",
    "description": "Descrição exemplo 2",
    "financeValue": 500.0,
    "id": 11, //id da despesa
    "user": {
        "id": 1, //id do autor
        "name": "Josias"
    }
}
````

### Deletar uma despesa

**DELETE** `http://localhost:8080/expenses/troque-pelo-id-autor/delete/troque-pelo-id-despesa`

**Response (STATUS)**
````
200 OK
````








