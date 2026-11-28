# Product API

## ProductController

The controller exposes `/api/products`. Send a bearer token for each operation. The stateless security chain allows ADMIN and USER to read and requires ADMIN for writes. Authentication failures return 401 and insufficient authority returns 403. The [security configuration](../../../src/main/java/com/example/productapi/config/SecurityConfig.java) and [MVC exception handler](../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java) use the shared [ErrorResponse](../../../src/main/java/com/example/productapi/exception/ErrorResponse.java) format.

Write bodies use [ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java). The successful response DTO is [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java), wrapped in [PageResponse](../../../src/main/java/com/example/productapi/dto/PageResponse.java) for list results.

| Method and path | Inputs | Successful response | Roles | Controller method |
| --- | --- | --- | --- | --- |
| GET `/api/products` | Paging, sort and optional filters | 200 with page | ADMIN, USER | [getAll](../../../src/main/java/com/example/productapi/controller/ProductController.java#L40) |
| GET `/api/products/{id}` | Product ID | 200 with product | ADMIN, USER | [getById](../../../src/main/java/com/example/productapi/controller/ProductController.java#L72) |
| POST `/api/products` | ProductRequest | 201 with product and Location | ADMIN | [create](../../../src/main/java/com/example/productapi/controller/ProductController.java#L78) |
| PUT `/api/products/{id}` | Product ID and ProductRequest | 200 with product | ADMIN | [update](../../../src/main/java/com/example/productapi/controller/ProductController.java#L85) |
| DELETE `/api/products/{id}` | Product ID | 204 with empty body | ADMIN | [delete](../../../src/main/java/com/example/productapi/controller/ProductController.java#L92) |

See the [feature list](OVERVIEW.md#features) for request rules and failure paths.

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
