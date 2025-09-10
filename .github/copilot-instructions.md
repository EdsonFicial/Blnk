# Copilot Instructions for blnk (Spring Boot API)

## Project Overview
- This is a Spring Boot REST API simulating a banking system.
- Main package: `pavaulla.firstapi.blnk`.
- Key components:
  - `controllers/`: REST endpoints (e.g., `UserController` for user CRUD)
  - `models/`: JPA entities (`UserEntity`, `TransactionEntity`)
  - `repository/`: Spring Data JPA repositories (`UserRepository`)
- Data is persisted in PostgreSQL (see `application.properties`).

## Build & Run
- Use Maven wrapper scripts: `./mvnw spring-boot:run` (Linux/macOS) or `mvnw.cmd spring-boot:run` (Windows)
- To build: `./mvnw clean package` or `mvnw.cmd clean package`
- Tests: `./mvnw test` or `mvnw.cmd test`
- App runs on default port 8080 unless overridden in `application.properties`.

## Database
- Default DB: PostgreSQL (`spring.datasource.url` in `application.properties`)
- Credentials are set via environment variables or fallback to defaults in `application.properties`.
- JPA auto-creates/updates schema (`spring.jpa.hibernate.ddl-auto=update`).

## API Patterns
- REST endpoints are under `/api/users` (see `UserController`).
- Standard CRUD via Spring Data JPA (`UserRepository`).
- Entities use JPA annotations; IDs are auto-generated.
- Example: `UserEntity` fields map to `users` table columns.

## Conventions & Patterns
- Use constructor and no-arg constructor for entities.
- Use Lombok if needed, but not required (see `pom.xml`).
- Exception handling and validation are minimal; add as needed.
- Use dependency injection (`@Autowired`) for repositories/services.

## Integration & Extensibility
- Add new endpoints in `controllers/`, new entities in `models/`, and repositories in `repository/`.
- For new DB tables, create a JPA entity and repository interface.
- External dependencies managed in `pom.xml`.

## References
- See `HELP.md` for official Spring/Maven docs and guides.
- Main entry: `BlnkApplication.java`.
- Config: `src/main/resources/application.properties`.

---

**Update this file if you add new major features, workflows, or conventions.**
