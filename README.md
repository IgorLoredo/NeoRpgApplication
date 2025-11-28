# Game Character API (NeoRpgApplication)

REST API to manage RPG characters. The project is structured following Clean Architecture principles (domain, application, infrastructure, shared).

## Overview
- Language: Java 21
- Framework: Spring Boot
- Architecture: Clean Architecture (layers: domain, application, infrastructure, shared)
- Persistence: In-memory (currently) — `InMemoryCharacterRepository`
- Default port (dev): 8081 (see `src/main/resources/application.yml`)

## Main endpoints
Base path: `/api/v1`

- POST /api/v1/characters
  - Creates a character
  - Body: JSON { "name": "string", "job": "WARRIOR|ROGUE|MAGE|PALADIN" }
  - Response: 201 Created with wrapper `{ "success": true, "data": { ... } }`

- GET /api/v1/characters/{id}
  - Retrieves a character by id
  - Response: 200 OK with wrapper `{ "success": true, "data": { ... } }` or 400/404

- GET /api/v1/characters
  - Lists characters (simple in-memory pagination)
  - Query params: `page` (default 0), `pageSize` (default 10)

- DELETE /api/v1/characters/{id}
  - Deletes a character
  - Response: 204 No Content

- POST /api/v1/battles?attackerId={id}&defenderId={id}&damage={int}
  - Executes a simple battle between characters
  - Response: 200 OK with `BattleResultResponse` wrapped

## How to run (PowerShell)
From the project root on Windows PowerShell:

```powershell
# Compile
& '.\mvnw.cmd' clean compile

# Run tests
& '.\mvnw.cmd' test

# Run the application
& '.\mvnw.cmd' spring-boot:run
```

The application will start on the port configured in `application.yml` (default 8081). If the port is in use, change `application.yml` or pass `--server.port=PORT` as an argument.

## Request examples
Create character (curl):

```bash
curl -X POST "http://localhost:8081/api/v1/characters" \
  -H "Content-Type: application/json" \
  -d '{"name":"Aragorn","job":"WARRIOR"}'
```

Create character (PowerShell - Invoke-RestMethod):

```powershell
$body = @{ name = 'Aragorn'; job = 'WARRIOR' } | ConvertTo-Json
Invoke-RestMethod -Uri 'http://localhost:8081/api/v1/characters' -Method Post -ContentType 'application/json' -Body $body
```

Get character:

```bash
curl http://localhost:8081/api/v1/characters/{id}
```

Execute a battle (example):

```bash
curl -X POST "http://localhost:8081/api/v1/battles?attackerId={attackerUuid}&defenderId={defenderUuid}&damage=20"
```

## Swagger / OpenAPI
After starting the application, the Swagger UI (springdoc) should be available at:

- Swagger UI: `http://localhost:8081/swagger-ui/index.html` (or `.../swagger-ui.html`)
- OpenAPI JSON: `http://localhost:8081/v3/api-docs`

> Note: If `springdoc` is installed and the application starts, those endpoints will show the automatic API documentation of the controllers.

## Tests
- Unit and integration tests are in `src/test/java`.
- Run tests with (PowerShell):

```powershell
& '.\mvnw.cmd' test
```

## Architecture and next steps
- The `domain` layer contains entities and value objects (Character, CharacterId, Health, Stats, etc.).
- The `application` layer contains use cases (services) and DTOs (commands/queries).
- The `infrastructure` layer contains REST controllers, the in-memory repository and bean configuration.
- The `shared` layer contains exception handling and tracing/MDC filters.

Possible improvements:
- Replace `InMemoryCharacterRepository` with JPA persistence (H2/Postgres) and `JpaCharacterRepository`.
- Add database migrations (Flyway/Liquibase).
- Add authentication/authorization (JWT/OAuth2).
- Add more integration tests and increase coverage.
- Improve pagination and filtering in the listing endpoint.

## Notes
- `application.yml` configures port `8081` and serialization options.
- Tracing and logging: `TraceIdFilter` and `MDCFilter` add trace id support in logs.

---

If you want, I can:
- Run the build and tests now to validate everything (`run`).
- Add a Postman collection example (`postman`).
- Convert the in-memory repository to JPA/H2 with an `@Entity` implementation (`jpa`).

README updated: `README.md`