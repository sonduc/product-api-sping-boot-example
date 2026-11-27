# Local development

The project README holds the local run procedure. Follow it in this order.

1. Check the Docker daemon and install the VS Code Dev Containers extension. [Prerequisites](../README.md#yêu-cầu)
2. Copy `.env.example` to `.env`, choose host ports, reopen the repository in the devcontainer, and run `mvn spring-boot:run` there. Compose starts PostgreSQL and supplies the development datasource. Flyway initializes the schema and seed data at startup. [Quick start](../README.md#quick-start) · [Compose](../docker-compose.yml) · [Flyway configuration](../src/main/resources/application.yml#L14)
3. Open `/actuator/health` at the configured app host port, which defaults to `18082`. The README checklist expects HTTP 200. To exercise a protected endpoint, follow the README's token example. [Ports and health URL](../README.md#port-mapping) · [Auth flow](../README.md#auth-flow) · [Project checklist](../README.md#checklist-cuối-project)
4. Stop the Maven process in the devcontainer terminal. To stop the Compose services, use the host `docker: down` task. [Quick start](../README.md#quick-start) · [VS Code tasks](../.vscode/tasks.json)

For port conflicts, the Docker socket, and startup/debug issues, see [Troubleshooting](../README.md#troubleshooting).
