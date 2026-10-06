# Testcontainers Reference

## When to load

Load for integration tests with Postgres via Testcontainers.

## Dependencies

`testcontainers`, `testcontainers-junit-jupiter`, `testcontainers-postgresql`.

## Setup

```java
@Testcontainers
@SpringBootTest
class ProductIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void createAndFind() {
        // ...
    }
}
```

## Patterns

- `@Container` static for a shared DB.
- `@DynamicPropertySource` to wire the datasource.
- Flyway runs automatically on startup.
- `@BeforeEach` cleanup for test isolation.

## Coverage Gates

JaCoCo must satisfy line >= 70%, branch >= 60% at `mvn clean verify`.
