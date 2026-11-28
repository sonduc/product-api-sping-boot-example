# Coding rules

These conventions apply when changing the Product HTTP flow.

- Use request and response DTOs at the controller boundary. Bind write bodies with `@Valid` and map entities to response DTOs in the service. [ProductController](../src/main/java/com/example/productapi/controller/ProductController.java), [ProductMapper](../src/main/java/com/example/productapi/mapper/ProductMapper.java).
- Route validation, missing-resource and optimistic-lock errors through the shared exception handler. Keep the response in the shared `ErrorResponse` format. [GlobalExceptionHandler](../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java), [ErrorResponse](../src/main/java/com/example/productapi/exception/ErrorResponse.java).
- Keep route permissions in the security chain and retain method-level ADMIN restrictions on Product mutations. [SecurityConfig](../src/main/java/com/example/productapi/config/SecurityConfig.java), [ProductController](../src/main/java/com/example/productapi/controller/ProductController.java).
- Use read-only transactions for Product reads and write transactions for mutations. Keep detail-cache changes coordinated with successful writes. [ProductService](../src/main/java/com/example/productapi/service/ProductService.java), [CacheConfig](../src/main/java/com/example/productapi/config/CacheConfig.java).
- Add a new Flyway migration for schema changes. Keep JPA mappings compatible with schema validation. [Migrations](../src/main/resources/db/migration), [application configuration](../src/main/resources/application.yml).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
