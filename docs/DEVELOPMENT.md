# Local development

## Environment requirements

Use Docker with Compose and VS Code Dev Containers. The container supplies Java 21 and Maven 3.9. [Dev Container Dockerfile](../.devcontainer/Dockerfile).

## Local configuration and initialization

Follow the [README quick start](../README.md#quick-start) to copy [.env.example](../.env.example), configure host ports and open the devcontainer. [Compose](../docker-compose.yml) defines the application and PostgreSQL services. Flyway applies migrations at application startup. [Application configuration](../src/main/resources/application.yml).

## Run and stop

Use the launch command in the [quick start](../README.md#quick-start) and the health URL in [Port mapping](../README.md#port-mapping). Stop the application from its terminal. Use the Compose shutdown procedure in [Troubleshooting](../README.md#troubleshooting) to stop the local services and retain the database volume.

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
