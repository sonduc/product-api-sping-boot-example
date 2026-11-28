# Product

## Responsibility

Product manages the catalog records exposed by [ProductController](../../../src/main/java/com/example/productapi/controller/ProductController.java). This overview covers all five operations in that controller.

## Features

| Feature | Outcome |
| --- | --- |
| [List products](features/list-products.md) | Return a filtered, sorted page of products |
| [Get a product](features/get-product.md) | Return one product by ID |
| [Create a product](features/create-product.md) | Persist a new product and return its location |
| [Update a product](features/update-product.md) | Replace the editable fields of an existing product |
| [Delete a product](features/delete-product.md) | Remove an existing product |

## Components

- [API](api.md) for routes, roles and DTOs
- [Data model](data-model.md) for the product table and concurrency fields
- [Testing](TESTING.md) for checks by behavior

## Relationships with other modules

Product requests depend on the shared security layer. The JWT filter uses `AuthService.loadUserByUsername` to resolve the caller and builds authentication with the loaded authorities. [JwtAuthenticationFilter](../../../src/main/java/com/example/productapi/security/JwtAuthenticationFilter.java), [AuthService](../../../src/main/java/com/example/productapi/service/AuthService.java).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
