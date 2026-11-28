# Update a product

## Overview

Replace the editable fields of an existing product.

## Entry point, inputs and outputs

| Request | Successful response | Code |
| --- | --- | --- |
| `PUT /api/products/{id}` with a Long ID and JSON ProductRequest | `200 OK` with ProductResponse | [ProductController.update](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L85) |

## Conditions and rules

ADMIN is required. [SecurityConfig](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java).

The request requires a nonblank name of at most 255 characters, a price greater than zero with at most 10 integer and 2 fractional digits, and an integer stock value of at least zero. Description is optional. Constraint violations return 400 with field details. [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java).

The body supplies name, description, price and stock. Persistence version is internal. Overlapping writes can conflict when they read the same version. A stale client form submitted after an earlier transaction commits can still overwrite the current fields. [ProductMapper.updateEntity](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java), [Product](../../../../src/main/java/com/example/productapi/domain/Product.java).

## Flow and impacts

`ProductService.update` looks up the product, applies the replacement fields, saves and flushes under a write transaction, and maps the result. A missing product returns 404. An invalid ID type returns 400. An optimistic-lock failure rolls the transaction back and maps to 409 through the shared handler. [ProductService](../../../../src/main/java/com/example/productapi/service/ProductService.java), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java).

The write clears the entire `products` detail cache on success. The transaction-aware manager defers eviction until commit when synchronization is active. [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java).

## Verification

CRUD integration assertions cover the update, preserved creation time, refreshed detail and the 200/409 outcomes for concurrent updates. See [Product testing](../TESTING.md).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
