# Question and Answer Project

## Overview

The Question and Answer project is a Spring Boot application that allows users to create, retrieve, and manage questions and answers. It supports different question types, including polls and trivia questions. This project utilizes Spring Data JPA for database interactions, Lombok for reducing boilerplate code, and MapStruct for object mapping.

## Features

- Create, retrieve, vote questions
- Support for multiple question types (e.g., trivia, polls)
- RESTful API for easy integration with front-end applications
- Unit tests for service, controller, and repository layers

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (for in-memory testing)
- Lombok
- MapStruct
- JUnit 5
- Mockito

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle



### API Documentation

Base URL
All API endpoints are accessible from http://localhost:8080/api/questions.

Endpoints
1. Create a Question
   Endpoint: POST /api/questions

Request Body:

```
{
    "text": "What is the result of 2+3=",
    "answers": ["4", "5", "6", "7"],
    "correctAnswer": "5",
    "type": "TRIVIA" 
}  
```



Response:

Status Code: 200 OK
Body:

```
{
    "id": 1
}
```

----------

2. Get a Question by ID
   Endpoint: GET /api/questions/{id}

Response:

Status Code: 200 OK

Body:

```
{
    "id": 1,
    "text": "What is the result of 2+3=",
    "answers": [
        "4",
        "5",
        "6",
        "7"
    ],
    "correctAnswer": "5",
    "type": "TRIVIA",
    "votes": {}
}

```



--------

3.Vote a Question

URL: POST /api/questions/{id}/vote

Path Variable:
id: The ID of the question you are voting on.

Query Parameter:
answer: The answer that is being voted for.

```
POST /api/questions/1/vote?answer=5
```

Response:

```
{
    "votes": {
        "5": 1
    },
    "questionType": "POLL",
    "correct": false
}
```


# API Endpoints Summary

| Method | URL                        | Request Body / Params                                                         | Response                               |
|--------|----------------------------|-------------------------------------------------------------------------------|----------------------------------------|
| POST   | /api/questions             | { "text": "...", "answers": [...], "correctAnswer": "...", "type": "TRIVIA" } | Long (ID of the created question)      |
| GET    | /api/questions/{id}        | -                                                                             | QuestionDTO (details of the question) |
| POST   | /api/questions/{id}/vote   | Query Parameter: answer (e.g., answer=5)                                      | VoteResponseDTO (votes and correctness)|
