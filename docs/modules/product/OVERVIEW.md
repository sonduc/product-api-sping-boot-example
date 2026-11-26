# Product

Product manages catalog records and their stock quantities. This documentation covers the five operations exposed by [ProductController](../../../src/main/java/com/example/productapi/controller/ProductController.java#L26).

| Page | What it covers |
| --- | --- |
| [API](api.md) | Request and response types, status codes, and access rules |
| [Product CRUD](features/crud.md) | Filtering, persistence, cache invalidation, and concurrent writes |
| [Data model](data-model.md) | Product table, timestamps, and optimistic locking |
| [Testing](TESTING.md) | Checks by change type, prerequisites, and coverage gaps |

## Dependencies

| Dependency | Role |
| --- | --- |
| Security | The filter resolves token identity against the stored user and the security chain checks roles before the controller handles the request. [JWT filter](../../../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java#L28) · [Security config](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L37) |
| Persistence and mapping | `ProductService` calls `ProductRepository` and maps entities to DTOs before returning to the controller. [Service](../../../src/main/java/com/example/productapi/service/ProductService.java#L29) · [Repository](../../../src/main/java/com/example/productapi/repository/ProductRepository.java#L7) |
| Cache | The service uses the transaction-aware `products` cache for detail reads and clears it on successful writes. [Service](../../../src/main/java/com/example/productapi/service/ProductService.java#L35) · [Cache config](../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14) |

[Documentation map](../../README.md)

<!-- spring-docs:verification:start -->
## Verification

- Document status — Draft.
- Source snapshot — Local commit `63ea63b435d84bf07ac311eff8e7483bce3135eb` on `feat/apply-codex-kit-ver5`.
- Code review — Unconfirmed.
- Scope — The five Product endpoints, including their DTOs, query rules, persistence, cache behavior, and existing checks.
- Dependencies — Shared JWT and security rules, error handling, cache configuration, Flyway schema and Maven test configuration.
- Documentation verification — Complete for source comparison and navigation at `63ea63b435d84bf07ac311eff8e7483bce3135eb` on `2026-11-25T12:17:22+07:00`.
- Content updated — `2026-11-25T12:17:22+07:00`.
- Limits — Source inspection only. Maven tests and the application were not run.
<!-- spring-docs:verification:end -->
