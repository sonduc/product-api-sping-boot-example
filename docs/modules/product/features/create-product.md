# Create a product

## Overview

Persist a new catalog record and expose its generated resource URI.

## Entry point, inputs and outputs

| Request | Successful response | Code |
| --- | --- | --- |
| `POST /api/products` with JSON ProductRequest | `201 Created` with ProductResponse and `Location: /api/products/{id}` | [ProductController.create](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L78) |

## Conditions and rules

ADMIN is required. [SecurityConfig](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java).

The request requires a nonblank name of at most 255 characters, a price greater than zero with at most 10 integer and 2 fractional digits, and an integer stock value of at least zero. Description is optional. Constraint violations return 400 with field details. [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java).

## Flow and impacts

`ProductService.create` maps the request to a new entity, saves and flushes it in a write transaction, and maps the saved entity to the response. `Product.onCreate` initializes both timestamps. The controller builds the Location header from the generated ID. [ProductService](../../../../src/main/java/com/example/productapi/service/ProductService.java), [ProductMapper](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java), [Product](../../../../src/main/java/com/example/productapi/domain/Product.java).

The write clears the entire `products` detail cache on success. The transaction-aware manager defers eviction until commit when synchronization is active. [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java).

## Verification

The integration helper asserts 201, a Location header and a body. The CRUD suite asserts 400 with field errors for an invalid POST. See [Product testing](../TESTING.md).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
