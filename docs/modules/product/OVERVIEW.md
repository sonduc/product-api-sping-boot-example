# Product

This guide covers the five HTTP operations in `ProductController`.

## Responsibility

The module lets ADMIN and USER accounts browse the catalog and read individual products. ADMIN accounts can create, update, and delete product records. [ProductController](../../../src/main/java/com/example/productapi/controller/ProductController.java#L26) · [SecurityConfig](../../../src/main/java/com/example/productapi/config/SecurityConfig.java#L47)

## Features

| Feature | Outcome |
| --- | --- |
| [List products](features/list-products.md) | Return a filtered, sorted page with pagination metadata. |
| [Get a product](features/get-product.md) | Return product details by ID using the DTO cache. |
| [Create a product](features/create-product.md) | Persist a new product and return its resource URI. |
| [Update a product](features/update-product.md) | Replace editable fields on an existing product. |
| [Delete a product](features/delete-product.md) | Remove an existing product. |

## Components

| Guide | Scope |
| --- | --- |
| [API](api.md) | HTTP routes, DTOs, authorization, and error handling |
| [Data model](data-model.md) | JPA entity, Flyway migrations, and optimistic locking |
| [Testing](TESTING.md) | Checks and coverage gaps for the supported operations |

## Project dependencies

The shared JWT filter loads the user's current authorities through `AuthService` before the security chain authorizes product requests. The Product service reads and writes through `ProductRepository` and uses the shared cache manager for product detail responses. [JWT filter](../../../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java#L29) · [ProductService](../../../src/main/java/com/example/productapi/service/ProductService.java#L19) · [CacheConfig](../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14)
