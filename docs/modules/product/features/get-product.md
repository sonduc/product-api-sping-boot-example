# Get a product

## Overview

Read a catalog item by ID. Successful reads can reuse a cached DTO.

## Entry, inputs, and outputs

`GET /api/products/{id}` takes a `Long` ID. ADMIN and USER accounts can call it. Success returns HTTP 200 with `ProductResponse`. [ProductController.getById](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L71) · [Access rules and DTO](../api.md)

## Conditions and rules

An ID that cannot be converted to `Long` returns HTTP 400. A product that cannot be found on lookup returns HTTP 404. [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L35) · [ProductService.findProduct](../../../../src/main/java/com/example/productapi/service/ProductService.java#L64)

## Flow and impacts

The service uses the product ID as the key in the in-memory `products` cache. On a cache miss, it loads the entity in a read-only transaction and maps it to a response DTO. A missing row raises `ResourceNotFoundException`. Successful create, update, and delete operations clear the whole product cache. The cache manager defers mutations when a transaction is active so they take effect after commit. [ProductService](../../../../src/main/java/com/example/productapi/service/ProductService.java#L35) · [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14)

## Verification and gaps

See the detail, cache, and HTTP checks in the [Product testing guide](../TESTING.md).
