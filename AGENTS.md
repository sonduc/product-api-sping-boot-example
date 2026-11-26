# Agent working instructions

This repository contains the Spring Boot backend for `product-app`. Check the current branch and working tree before editing, and preserve changes outside the task.

| Need | Where to read |
| --- | --- |
| Stack, local setup and ports | [README](README.md#stack) and [Quick start](README.md#quick-start) |
| Request flow and layer responsibilities | [Architecture](README.md#kiến-trúc) |
| Product API and behavior | [Product overview](docs/modules/product/OVERVIEW.md) |
| Product verification | [Product testing guide](docs/modules/product/TESTING.md) |
| Project-wide test commands and prerequisites | [README test section](README.md#test) |
| Other documented areas | [Documentation map](docs/README.md) |

- Run Maven commands from the devcontainer. Use `mvn test` for unit, MVC and H2 repository tests and `mvn verify` for the additional PostgreSQL integration tests.
- Use the Testcontainers database for integration tests. See the README for Docker socket configuration and test reports.
- Add a new Flyway migration for schema changes. Preserve migrations already applied to a database.
- Trace changed behavior through its controller, dependencies, and relevant tests. Report source inspection and executed checks separately.
