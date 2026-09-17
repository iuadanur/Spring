# Job Application Tracker API

A Spring Boot REST API built step by step to practice Java backend development and common production-oriented patterns.

## Tech Stack

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Docker & Docker Compose
- JUnit
- Mockito
- H2
- SLF4J

## Project Roadmap

| Step | Topic | Purpose |
|---|---|---|
| 1 | Spring Boot Setup | Create the project with Java 17, Maven and Spring Web |
| 2 | Domain Model | Create `JobApplication` and `ApplicationStatus` |
| 3 | Service Layer | Separate business logic using `@Service` |
| 4 | Controller Layer | Expose REST endpoints using `@RestController` |
| 5 | Dependency Injection | Use constructor-based dependency injection |
| 6 | CRUD API | Implement GET, POST, PUT and DELETE endpoints |
| 7 | HTTP Responses | Use `ResponseEntity` and proper HTTP status codes |
| 8 | PostgreSQL | Replace in-memory storage with persistent storage |
| 9 | Spring Data JPA | Simplify database access |
| 10 | Hibernate | Map Java entities to PostgreSQL tables |
| 11 | Repository Layer | Use `JpaRepository` for database operations |
| 12 | DTOs | Separate API models from database entities |
| 13 | Validation | Validate incoming requests with Jakarta Validation |
| 14 | Exception Handling | Add global exception handling with `@RestControllerAdvice` |
| 15 | Filtering | Filter applications by status |
| 16 | Search | Search applications by company name |
| 17 | Combined Queries | Filter by company and status together |
| 18 | Service Tests | Test business logic using JUnit and Mockito |
| 19 | Controller Tests | Test REST endpoints using MockMvc |
| 20 | Repository Tests | Test JPA queries using `@DataJpaTest` |
| 21 | Integration Tests | Test the full application flow |
| 22 | Environment Variables | Move configuration outside source code |
| 23 | Logging | Add application logging with SLF4J |
| 24 | Docker | Containerize the Spring Boot application |
| 25 | Docker Compose | Run Spring Boot and PostgreSQL together |

## API Examples

```text
GET    /applications
GET    /applications/{id}
POST   /applications
PUT    /applications/{id}
DELETE /applications/{id}

GET /applications?status=INTERVIEW
GET /applications?company=google
GET /applications?status=INTERVIEW&company=google