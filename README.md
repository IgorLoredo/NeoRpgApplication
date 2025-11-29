# Game Character API (NeoRpgApplication)

Backend API for the Neo take-home assignment: manage RPG characters (create/list/detail) and run battles.

## Overview
- Language: Java 21
- Framework: Spring Boot
- Architecture: Clean Architecture (domain, application, infrastructure, shared)
- Persistence: In-memory only (`InMemoryCharacterRepository`)
- Default port: 8081 (see `src/main/resources/application.yml`)
- Health/metrics: `/actuator/health`, `/actuator/health/liveness`, `/actuator/health/readiness`, `/actuator/metrics`

## Requirements coverage
- Jobs: `WARRIOR`, `THIEF`, `MAGE` with correct base HP/stats and attack/speed modifiers from the spec.
- Name validation: 4–15 characters, letters or underscore only.
- Battle algorithm: speed roll per round, higher starts; on tie reroll. Damage roll per turn, HP cannot go below 0. Battle ends when one dies; log includes speed rolls, damage, and winner with remaining HP. HP persists after battle.
- State in memory (no database).
- Tests: unit (domain, services, repo) and integration (controllers/battle).

## Endpoints (base `/api/v1`)
- `POST /characters` — create character. Body: `{"name":"Aragorn","job":"WARRIOR|THIEF|MAGE"}`. Response: 201 with wrapped payload.
- `GET /characters/{id}` — character detail with stats/modifiers/HP.
- `GET /characters` — list characters (name, job, alive/dead).
- `POST /battles?attackerId={id}&defenderId={id}` — run battle; returns winner/loser, remaining HP, and log.

## How to run (PowerShell)
```powershell
# Compile
.\mvnw.cmd clean compile

# Run tests
.\mvnw.cmd test

# Run the app
.\mvnw.cmd spring-boot:run
```
The app starts on port 8081 (override with `--server.port=PORT`).

## Swagger / OpenAPI
- UI: `http://localhost:8081/swagger-ui/index.html`
- JSON: `http://localhost:8081/v3/api-docs`

## Architecture
- **Domain**: entities/value objects (Character, Job, CharacterId, Health, Stats).
    - Located under `src/main/java/com/neo/game/domain`.
- **Application**: use cases (services), DTOs (commands/queries), mappers.
- **Infrastructure**: REST controllers, in-memory repository, configuration, resilience (retry/rate limit), actuator.
- **Shared**: exception handling, random provider, logging utilities.

## Resilience & SRE
- Retries on character/battle services (Resilience4j).
- Rate limiting on battle endpoint.
- Metrics: counters for creations/battles; timers for battle execution; Actuator health/readiness/liveness.

## Examples
Create character:
```bash
curl -X POST "http://localhost:8081/api/v1/characters" \
  -H "Content-Type: application/json" \
  -d '{"name":"Aragorn","job":"WARRIOR"}'
```
Battle:
```bash
curl -X POST "http://localhost:8081/api/v1/battles?attackerId={attackerUuid}&defenderId={defenderUuid}"
```
