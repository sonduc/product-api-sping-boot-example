# Delete a product

## Overview

Remove an existing catalog item by ID.

## Entry, inputs, and outputs

`DELETE /api/products/{id}` takes a `Long` ID and requires ADMIN. Success returns HTTP 204 with an empty body. [ProductController.delete](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L90) · [Access rules](../api.md)

## Conditions and rules

An ID that cannot be converted to `Long` returns HTTP 400. A product missing at lookup returns HTTP 404, including when a client repeats a completed deletion while the ID is still absent. [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L35) · [ProductService.findProduct](../../../../src/main/java/com/example/productapi/service/ProductService.java#L64)

## Flow and impacts

In a write transaction, the service finds the entity, calls `delete`, and flushes. A successful deletion clears the whole product detail cache after the write. If the persistence layer signals an optimistic locking failure, the shared handler maps it to HTTP 409 and the failed write rolls back. [ProductService.delete](../../../../src/main/java/com/example/productapi/service/ProductService.java#L57) · [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14) · [GlobalExceptionHandler.handleOptimisticConflict](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L65)

## Verification and gaps

The [Product testing guide](../TESTING.md) covers HTTP 204 and the detail lookup after deletion. It also records the missing-ID write coverage gap.
