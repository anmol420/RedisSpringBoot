# BookAPI

A high-performance RESTful API built with Spring Boot to manage books. This application integrates Redis caching and PostgreSQL persistence using Docker, and follows clean architecture principles with DTOs, services, and mappers.

---

## Features

- Add, update, delete, and fetch books using clean REST endpoints
- PostgreSQL for persistent data storage
- Redis `CacheManager` for low-latency cache
- MapStruct for DTO ↔ Entity mapping
- Docker Compose for infrastructure (Postgres, Redis)
- H2 in-memory DB support for testing
- Lombok for reducing boilerplate

---

## Tech Stack

| Technology        | Description                  |
|-------------------|------------------------------|
| Java 21           | Core programming language    |
| Spring Boot       | Application framework        |
| Spring Web        | RESTful APIs                 |
| Spring Data JPA   | Database ORM                 |
| Spring Data Redis | Redis caching                |
| PostgreSQL        | Production database          |
| H2                | Testing database (in-memory) |
| MapStruct 1.6.3   | DTO ↔ Entity mapping         |
| Jackson JSR310    | Java Time JSON support       |
| Lombok            | Code boilerplate reduction   |
| Docker Compose    | Containerized setup          |

---

## API Endpoints

| Method | Endpoint                             | Description              |
|--------|--------------------------------------|--------------------------|
| POST   | `/api/v1/book/addBook`               | Add a new book           |
| GET    | `/api/v1/book/getBooks`              | Get all books            |
| GET    | `/api/v1/book/getBookById/{id}`      | Get book by ID           |
| PATCH  | `/api/v1/book/updateBook/{id}`       | Update book details      |
| DELETE | `/api/v1/book/deleteBook/{id}`       | Delete a book            |

All endpoints return standard JSON responses and use validation for request bodies.

---

## Running the Project

1. **Start required services using Docker:**

   ```bash
   docker-compose up -d
   ```
2. **Run the app:**
    ```bash
   ./mvnw spring-boot:run
    ```
