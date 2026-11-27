# Update a product

## Overview

Replace the editable fields of an existing catalog item and return the updated representation.

## Entry, inputs, and outputs

`PUT /api/products/{id}` takes a `Long` ID and a JSON `ProductRequest`. It requires ADMIN and returns HTTP 200 with `ProductResponse` on success. [ProductController.update](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L83) · [Access rules](../api.md)

## Conditions and rules

The body requires nonblank `name` of at most 255 characters, positive `price` with at most ten integer digits and two fractional digits, and nonnegative integer `stock`. All four editable fields are assigned from the request, so omitting optional `description` clears it. Invalid inputs return HTTP 400, with `details` populated for field validation errors. [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [ProductMapper.updateEntity](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L27) · [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L23)

## Flow and impacts

The service finds the product, applies the request, and calls `saveAndFlush` within a write transaction. A missing product returns HTTP 404. When JPA updates the row, its callback refreshes `updatedAt` and preserves `createdAt`. The detail cache clears after a successful write. [ProductService.update](../../../../src/main/java/com/example/productapi/service/ProductService.java#L49) · [ProductService.findProduct](../../../../src/main/java/com/example/productapi/service/ProductService.java#L64) · [Product callbacks](../../../../src/main/java/com/example/productapi/domain/Product.java#L55) · [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14)

If overlapping writes read the same entity version and one commits a change first, an optimistic locking failure rolls back the stale write and returns HTTP 409. The client can reload and retry. The request has no client version, so a stale form submitted after the earlier transaction has committed can overwrite editable fields. [Product.version](../../../../src/main/java/com/example/productapi/domain/Product.java#L23) · [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [GlobalExceptionHandler.handleOptimisticConflict](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L65)

## Verification and gaps

See the HTTP update, concurrency, and missing-ID coverage in the [Product testing guide](../TESTING.md).
