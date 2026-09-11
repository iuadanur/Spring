# Job Application Tracker API

A Spring Boot REST API project that reviews Spring Boot concepts.

## Project Roadmap

| Step | Topic | Purpose |
|---|---|---|
| 1 | Spring Boot Setup | Create the base project with Java 17, Maven and Spring Web |
| 2 | Domain Model | Create `JobApplication` and `ApplicationStatus` |
| 3 | Service Layer | Separate business logic using `@Service` |
| 4 | Controller Layer | Handle HTTP requests using `@RestController` |
| 5 | Dependency Injection | Inject `JobApplicationService` through the constructor |
| 6 | GET Endpoint | Retrieve all job applications |
| 7 | POST Endpoint | Create job applications using `@RequestBody` |
| 8 | JSON Deserialization | Add a no-args constructor so Jackson can create objects from JSON |
| 9 | Get by ID | Use `GET /applications/{id}` and `@PathVariable` |
| 10 | Update Application | Use `PUT /applications/{id}` |
| 11 | Delete Application | Use `DELETE /applications/{id}` |
| 12 | HTTP Status Codes | Use `200`, `201`, `204`, `404` correctly |
| 13 | PostgreSQL | Add persistent database storage |
| 14 | Spring Data JPA | Connect Java objects with relational database operations |
| 15 | JPA Entity | Add `@Entity`, `@Id`, `@GeneratedValue` |
| 16 | Repository Layer | Create a repository using `JpaRepository` |
| 17 | Hibernate | Use ORM to map Java entities to database tables |
| 18 | DTOs | Separate API request/response models from entities |
| 19 | Validation | Add `@Valid`, `@NotBlank`, `@NotNull` |
| 20 | Exception Handling | Add custom exceptions and `@ControllerAdvice` |
| 21 | Filtering | Filter applications by status using query parameters |
| 22 | Search | Search applications by company name |
| 23 | Unit Tests | Test service/business logic |
| 24 | Controller Tests | Test REST endpoints |
| 25 | Repository Tests | Test database operations |
| 26 | Integration Tests | Test the complete application flow |
| 27 | Environment Variables | Move configuration outside the source code |
| 28 | Logging | Add useful application and error logs |
| 29 | Docker | Containerize the Spring Boot application |
| 30 | Docker Compose | Run Spring Boot and PostgreSQL together |
| 31 | Swagger / OpenAPI | Document and test REST endpoints |