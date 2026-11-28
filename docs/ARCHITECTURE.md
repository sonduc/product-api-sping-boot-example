# Project architecture

See the [project README](../README.md#stack) for stack versions and [Development](DEVELOPMENT.md) for local setup.

## Code structure

| Package or resource | Responsibility | Example |
| --- | --- | --- |
| `config`, `security` | Authenticate requests and authorize routes | [SecurityConfig](../src/main/java/com/example/productapi/config/SecurityConfig.java), [JwtAuthenticationFilter](../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java) |
| `controller`, `dto`, `exception` | Bind HTTP inputs and produce responses | [ProductController](../src/main/java/com/example/productapi/controller/ProductController.java), [GlobalExceptionHandler](../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java) |
| `service`, `mapper` | Define transaction boundaries and map product data | [ProductService](../src/main/java/com/example/productapi/service/ProductService.java), [ProductMapper](../src/main/java/com/example/productapi/mapper/ProductMapper.java) |
| `repository`, `domain`, `db/migration` | Query and persist product data in PostgreSQL | [ProductRepository](../src/main/java/com/example/productapi/repository/ProductRepository.java), [Product](../src/main/java/com/example/productapi/domain/Product.java), [initial schema](../src/main/resources/db/migration/V1__init_schema.sql) |

## Main processing flow

For [listing products](modules/product/features/list-products.md), the client sends an authenticated request through the stateless JWT filter and the route authorization rules. `ProductController.getAll` binds the inputs and builds a pageable. `ProductService.getAll` executes a specification-backed repository query in a read-only transaction and maps each result to a DTO. The controller returns HTTP 200 with a page of DTOs to the client.

Errors raised during MVC binding or service execution are mapped by `GlobalExceptionHandler`. The security chain handles authentication and authorization failures before the controller.

## Architectural constraints

JPA open-in-view is disabled and Flyway owns schema changes. Product caching uses a transaction-aware, in-memory cache scoped to the application instance. [Application configuration](../src/main/resources/application.yml), [CacheConfig](../src/main/java/com/example/productapi/config/CacheConfig.java).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
