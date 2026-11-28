# Delete a product

## Overview

Remove the product identified by the request.

## Entry point, inputs and outputs

| Request | Successful response | Code |
| --- | --- | --- |
| `DELETE /api/products/{id}` with a Long ID | `204 No Content` | [ProductController.delete](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L92) |

## Conditions and rules

ADMIN is required. [SecurityConfig](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java).

## Flow and impacts

`ProductService.delete` looks up the entity, deletes it and flushes the write transaction. The controller returns 204 when the service completes successfully. An absent product returns 404 and an invalid ID type returns 400. Optimistic-lock exceptions use the shared 409 mapping. [ProductService](../../../../src/main/java/com/example/productapi/service/ProductService.java), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java).

The write clears the entire `products` detail cache on success. The transaction-aware manager defers eviction until commit when synchronization is active. [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java).

## Verification

CRUD integration assertions cover 204 and a subsequent detail read returning 404. The testing guide records the gap for DELETE with an absent ID. See [Product testing](../TESTING.md).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
