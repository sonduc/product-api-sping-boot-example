# Create a product

## Overview

Persist a new catalog item and return its representation and resource URI.

## Entry, inputs, and outputs

`POST /api/products` takes a JSON `ProductRequest` and requires ADMIN. Success returns HTTP 201 with `ProductResponse` and `Location: /api/products/{id}`. [ProductController.create](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L76) · [Access rules](../api.md)

## Conditions and rules

`name` must contain a non-whitespace character and have at most 255 characters. `price` must be greater than zero with at most ten integer digits and two fractional digits. `stock` must be a nonnegative integer. `description` is optional. Field validation failures return HTTP 400 with field messages in `details`. Unreadable JSON also returns 400. [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [GlobalExceptionHandler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L23)

## Flow and impacts

The service maps the request to a new entity and uses `saveAndFlush` within a write transaction. The database generates the ID and the JPA persist callback sets both timestamps. The service maps the persisted entity to the response DTO. After a successful write, the detail cache is cleared. A rolled-back write preserves the prior cache. [ProductMapper.toEntity](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L20) · [ProductService.create](../../../../src/main/java/com/example/productapi/service/ProductService.java#L43) · [Product data model](../data-model.md) · [CacheConfig](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14)

## Verification and gaps

See the HTTP create, validation, and access checks in the [Product testing guide](../TESTING.md).
