# Product API

`ProductController` serves `/api/products` through the stateless security chain.

| Shared behavior | Details |
| --- | --- |
| Authentication | Send a bearer token. The JWT filter establishes authentication with authorities loaded through `AuthService`. The security entry point returns HTTP 401 when authentication is required. [JWT filter](../../../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java#L29) · [SecurityConfig](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L37) |
| Authorization | GET permits ADMIN and USER. Writes require ADMIN in both the filter chain and controller method checks. Insufficient authority returns HTTP 403. [SecurityConfig](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L47) · [ProductController](../../../src/main/java/com/example/productapi/controller/ProductController.java#L76) |
| Errors | Security and MVC return `ErrorResponse`. MVC maps malformed or invalid inputs to 400, missing resources to 404, and optimistic locking exceptions to 409. [ErrorResponse](../../../src/main/java/com/example/productapi/exception/ErrorResponse.java#L6) · [GlobalExceptionHandler](../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L23) |

| Endpoint | Input | Success | Roles | Handler and feature |
| --- | --- | --- | --- | --- |
| `GET /api/products` | Optional pagination, sort, and filters | 200 with `PageResponse<ProductResponse>` | ADMIN, USER | [getAll](../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) · [List](features/list-products.md) |
| `GET /api/products/{id}` | Product ID | 200 with `ProductResponse` | ADMIN, USER | [getById](../../../src/main/java/com/example/productapi/controller/ProductController.java#L71) · [Detail](features/get-product.md) |
| `POST /api/products` | `ProductRequest` | 201 with `ProductResponse` and `Location` | ADMIN | [create](../../../src/main/java/com/example/productapi/controller/ProductController.java#L76) · [Create](features/create-product.md) |
| `PUT /api/products/{id}` | Product ID and `ProductRequest` | 200 with `ProductResponse` | ADMIN | [update](../../../src/main/java/com/example/productapi/controller/ProductController.java#L83) · [Update](features/update-product.md) |
| `DELETE /api/products/{id}` | Product ID | 204 with an empty body | ADMIN | [delete](../../../src/main/java/com/example/productapi/controller/ProductController.java#L90) · [Delete](features/delete-product.md) |

DTO definitions are in [ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11), [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java#L6), and [PageResponse](../../../src/main/java/com/example/productapi/dto/PageResponse.java#L6).
