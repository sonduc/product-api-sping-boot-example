# List products

## Overview

Return a page of product records for catalog browsing, with optional filters and a stable order.

## Entry, inputs, and outputs

`GET /api/products` accepts `page`, `size`, `sort`, `name`, `minPrice`, `maxPrice`, and `inStock`. ADMIN and USER accounts can call it. Success returns HTTP 200 with `content`, `page`, `size`, `totalElements`, and `totalPages`. [ProductController.getAll](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) · [PageResponse](../../../../src/main/java/com/example/productapi/dto/PageResponse.java#L6) · [Access rules](../api.md)

## Conditions and rules

| Input | Rule |
| --- | --- |
| Pagination | The page index is zero-based and defaults to `0`. Size defaults to `10` and accepts `1` through `100`. [getAll](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) |
| Sort | Defaults to `createdAt,desc`. Supply one of `id`, `name`, `price`, `stock`, `createdAt`, or `updatedAt` with `asc` or `desc`. Sorting on a field other than `id` adds `id ASC` as the tie-breaker. [SORT_FIELDS](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L30) · [parseSort](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L56) |
| Name | Matches a case-insensitive substring after trimming. Blank input adds no condition. The characters `!`, `%`, and `_` match literally. [ProductSpecification.hasName](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L15) |
| Price | Each bound is optional, inclusive, and nonnegative. With both bounds, the minimum must be less than or equal to the maximum. [ProductSpecification.priceBetween](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L26) |
| Stock | `true` selects stock greater than zero, `false` selects stock equal to zero, and omission adds no condition. [ProductSpecification.inStock](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L40) |

## Flow and impacts

The controller validates pagination and sort, then builds `ProductFilter` and `PageRequest`. In a read-only transaction, the service combines the filter predicates with AND, queries the repository, and maps results to DTOs. The controller wraps the page. A query with no matches returns an empty `content` array. Invalid pagination, sort, price bounds, or parameter types return HTTP 400. [Controller](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) · [ProductService.getAll](../../../../src/main/java/com/example/productapi/service/ProductService.java#L29) · [ProductSpecification.build](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L50) · [Error mappings](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L35)

## Verification and gaps

The [Product testing guide](../TESTING.md) lists MVC, specification, and repository checks for this flow and records the PostgreSQL HTTP coverage gap.
