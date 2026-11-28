# List products

## Overview

Return a page of products matching the supplied filters.

## Entry point, inputs and outputs

| Request | Successful response | Code |
| --- | --- | --- |
| `GET /api/products` with optional `page`, `size`, `sort`, `name`, `minPrice`, `maxPrice` and `inStock` | `200 OK` with [PageResponse](../../../../src/main/java/com/example/productapi/dto/PageResponse.java) | [ProductController.getAll](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L40) |

## Conditions and rules

ADMIN and USER can read with a valid bearer token. [SecurityConfig](../../../../src/main/java/com/example/productapi/config/SecurityConfig.java).

- Page numbers start at 0. The default size is 10 and the allowed range is 1–100.
- The default sort is `createdAt,desc`. Supported fields are `id`, `name`, `price`, `stock`, `createdAt` and `updatedAt`. Direction is `asc` or `desc`. Sorts on fields other than `id` append an ascending ID tie-breaker. [ProductController.parseSort](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L56).
- Filters combine with AND. A nonblank name matches a case-insensitive substring, with `!`, `%` and `_` treated literally. Price bounds are inclusive and can be supplied independently. `inStock=true` selects positive stock, while `false` selects zero stock. [ProductSpecification](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java).

## Flow and impacts

The controller builds `PageRequest` and `ProductFilter`. `ProductService.getAll` queries the repository in a read-only transaction and maps rows to DTOs. The controller wraps the result with its pagination metadata. List results bypass the detail cache. [ProductService](../../../../src/main/java/com/example/productapi/service/ProductService.java).

Invalid pagination, sort, parameter types, negative price bounds or a minimum above the maximum return 400. [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java).

## Verification

MVC tests cover default paging and rejection of invalid input. Specification and H2 tests cover filtering. The reviewed HTTP integration suite checks list access. See [Product testing](../TESTING.md).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
