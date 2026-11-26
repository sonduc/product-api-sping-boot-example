# Product API

## Access

Send a JWT in the `Authorization: Bearer <token>` header. The stateless security chain grants reads to `ADMIN` and `USER`, and the controller also checks the `ADMIN` role on writes. The JWT filter loads the user before authenticating the request. [SecurityConfig](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L37) · [JWT filter](../../../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java#L28) · [Write methods](../../../src/main/java/com/example/productapi/controller/ProductController.java#L76)

## Operations

| Method and path | Input | Success response | Roles | Controller method |
| --- | --- | --- | --- | --- |
| `GET /api/products` | `page`, `size`, `sort`, `name`, `minPrice`, `maxPrice`, `inStock` | `200` with `PageResponse<ProductResponse>` | ADMIN, USER | [getAll](../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) |
| `GET /api/products/{id}` | Numeric product ID | `200` with `ProductResponse` | ADMIN, USER | [getById](../../../src/main/java/com/example/productapi/controller/ProductController.java#L71) |
| `POST /api/products` | `ProductRequest` | `201` with `ProductResponse` and `Location: /api/products/{id}` | ADMIN | [create](../../../src/main/java/com/example/productapi/controller/ProductController.java#L76) |
| `PUT /api/products/{id}` | Numeric product ID and `ProductRequest` | `200` with updated `ProductResponse` | ADMIN | [update](../../../src/main/java/com/example/productapi/controller/ProductController.java#L83) |
| `DELETE /api/products/{id}` | Numeric product ID | `204` with an empty body | ADMIN | [delete](../../../src/main/java/com/example/productapi/controller/ProductController.java#L90) |

The page response contains `content`, `page`, `size`, `totalElements`, and `totalPages`. See the DTO definitions for the write body and the returned product. [PageResponse](../../../src/main/java/com/example/productapi/dto/PageResponse.java#L6) · [ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java#L6)

## Errors

| Status | Condition |
| --- | --- |
| `400` | Invalid request data, field validation errors, or invalid list parameters |
| `401` | Missing or invalid authentication |
| `403` | Authenticated user lacks the required role |
| `404` | Product lookup by ID failed during read, update, or delete |
| `409` | Persistence reports an optimistic locking conflict |
| `500` | Unexpected exception handled by the controller advice |

The security handlers and controller advice return `ErrorResponse`. Body validation includes field names and messages in `details`. [ErrorResponse](../../../src/main/java/com/example/productapi/exception/ErrorResponse.java#L6) · [GlobalExceptionHandler](../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L20) · [Security errors](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L56) · [Product lookup](../../../src/main/java/com/example/productapi/service/ProductService.java#L64)

[CRUD behavior and query rules](features/crud.md) · [Product overview](OVERVIEW.md)
