# Architecture

## Code structure

| Location under `src/main/java/com/example/productapi` | Responsibility and example |
| --- | --- |
| `config`, `security` | Configure stateless HTTP security and establish authentication from a bearer token. [SecurityConfig](../src/main/java/com/example/productapi/config/SecurityConfig.java#L38) |
| `controller`, `dto` | Bind HTTP input and shape the response. [ProductController.getAll](../src/main/java/com/example/productapi/controller/ProductController.java#L39) |
| `service`, `mapper` | Define transaction boundaries and translate entities to response DTOs. [ProductService.getAll](../src/main/java/com/example/productapi/service/ProductService.java#L29) · [ProductMapper.toResponse](../src/main/java/com/example/productapi/mapper/ProductMapper.java#L12) |
| `repository`, `domain` | Query and persist the product model. [ProductRepository](../src/main/java/com/example/productapi/repository/ProductRepository.java#L7) · [Product](../src/main/java/com/example/productapi/domain/Product.java#L15) |
| `exception` | Map MVC exceptions to HTTP status and a JSON error body. [GlobalExceptionHandler](../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L20) |

## Main request flow

```text
Client sends GET /api/products
  → JWT filter and SecurityConfig authenticate and authorize the read
  → ProductController builds pagination, sort, and filter inputs
  → ProductService queries ProductRepository using ProductSpecification
  → PostgreSQL returns the page and the service maps each product to a DTO
  → ProductController returns HTTP 200 and PageResponse to the client
```

`ProductService.getAll` maps results inside a read-only transaction. The shared JPA configuration disables Open EntityManager in View, enables Flyway, and sets Hibernate to validate the schema. Product detail uses a process-local DTO cache and successful product writes clear that cache. [List products](modules/product/features/list-products.md) · [application.yml](../src/main/resources/application.yml#L4) · [ProductService](../src/main/java/com/example/productapi/service/ProductService.java#L29) · [CacheConfig](../src/main/java/com/example/productapi/config/CacheConfig.java#L14)

See the project README for the [stack](../README.md#stack) and the existing [architecture notes](../README.md#kiến-trúc). See [Development](DEVELOPMENT.md) to run the service.

## Verification

<!-- spring-docs:verification:start -->
- **Document status** Draft.
- **Source snapshot** Commit `8a3c58ce0bcf35cd37b2499356d0552fb50ae08f` on `feat/apply-codex-kit-ver4`.
- **Code review** Unconfirmed for this snapshot.
- **Verification status** Complete for static source comparison of the documented scope.
- **Code scope** The five `ProductController` operations, with their service, DTOs, mapper, repository, specifications, entity, migrations, and relevant test assertions.
- **Dependencies** Shared JWT security, MVC exception handling, cache and JPA configuration, Maven build, and devcontainer/Compose configuration.
- **Verified commit** `8a3c58ce0bcf35cd37b2499356d0552fb50ae08f`.
- **Verified at** 2026-11-27T17:39:11+07:00
- **Content updated at** 2026-11-27T17:39:11+07:00
- **Verification limits** Maven tests and application startup were not run. The documentation describes behavior and test assertions from the selected source.
<!-- spring-docs:verification:end -->
