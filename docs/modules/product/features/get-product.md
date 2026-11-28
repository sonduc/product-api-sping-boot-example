# Get a product

## Overview

Fetch the current detail response for one product ID.

## Entry point, inputs and outputs

| Request | Successful response | Code |
| --- | --- | --- |
| `GET /api/products/{id}` with a Long ID | `200 OK` with ProductResponse | [ProductController.getById](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L72) |

## Conditions and rules

ADMIN and USER can read with a valid bearer token. [SecurityConfig](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java).

## Flow and impacts

`ProductService.getById` uses the `products` cache keyed by ID. On a miss, the read-only service loads the entity and maps it to `ProductResponse`. An absent product raises `ResourceNotFoundException` and returns 404. An ID that cannot bind to a Long returns 400. [ProductService](../../../../src/main/java/com/example/productapi/service/ProductService.java), [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java).

## Verification

The cache test checks repeated detail retrieval. CRUD integration assertions cover the body, a read after updating, and 404 after deletion. See [Product testing](../TESTING.md).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
