# Testing

Run Maven commands from the devcontainer. Integration tests require a running Docker daemon and a reachable mounted socket so Testcontainers can start and connect to its own PostgreSQL instance. [Prerequisites and reports](../README.md#test) · [Compose configuration](../docker-compose.yml)

| Command | Scope |
| --- | --- |
| `mvn test` | Unit, MVC, and H2 repository tests named `*Test`. |
| `mvn verify` | Also runs `*IT` through Failsafe and generates the JaCoCo report at `target/site/jacoco/index.html`. |

Use `mvn verify` after changes that affect HTTP integration, database migrations, or behavior shared across layers. [Failsafe and JaCoCo configuration](../pom.xml#L131)

- [Product testing](modules/product/TESTING.md) maps the ProductController operations to targeted checks.
