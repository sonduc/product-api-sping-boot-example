# Coding rules

These conventions apply to the existing Spring MVC and Product code paths.

## HTTP and errors

- Bind request bodies to DTOs and apply `@Valid` for field validation. Map product entities to response DTOs before returning them from the service. [ProductController.create](../src/main/java/com/example/productapi/controller/ProductController.java#L76) · [ProductRequest](../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [ProductMapper.toResponse](../src/main/java/com/example/productapi/mapper/ProductMapper.java#L12)
- Use the shared `ErrorResponse` and exception mappings for MVC failures. Use `BusinessException` for invalid business input and `ResourceNotFoundException` for a missing resource. Keep internal exception details out of HTTP 500 responses. [GlobalExceptionHandler](../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L41)
- Keep product access rules aligned between the security filter chain and the `@PreAuthorize` checks on controller write methods. [SecurityConfig](../src/main/java/com/example/productapi/config/SecurityConfig.java#L47) · [ProductController](../src/main/java/com/example/productapi/controller/ProductController.java#L76)

## Persistence and cache

- Keep Product reads in read-only service transactions and writes in service transactions. [ProductService](../src/main/java/com/example/productapi/service/ProductService.java#L29)
- Add a new Flyway migration for a schema change once an earlier migration has been applied. [README troubleshooting](../README.md#troubleshooting) · [V4 migration](../src/main/resources/db/migration/V4__add_version_to_products.sql)
- Cache product-detail DTOs by ID. Keep cache changes transaction-aware so a rolled-back write preserves the prior cache contents. [ProductService](../src/main/java/com/example/productapi/service/ProductService.java#L35) · [CacheConfig](../src/main/java/com/example/productapi/config/CacheConfig.java#L14)
