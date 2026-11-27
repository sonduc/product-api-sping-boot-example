# Product data model

`Product` stores catalog data in `products`. The entity has scalar fields and the table has no foreign keys. [Product entity](../../../src/main/java/com/example/productapi/domain/Product.java#L15) · [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8)

| Concern | Mapping and behavior |
| --- | --- |
| Identity | `Product.id` uses JPA identity generation and maps to the `BIGSERIAL` primary key. [Product.id](../../../src/main/java/com/example/productapi/domain/Product.java#L19) · [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8) |
| Catalog fields | `name`, `price`, and `stock` are required in the schema. `description` is nullable text. Price is stored as `NUMERIC(12, 2)`. [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8) |
| Timestamps | JPA `onCreate` initializes both `created_at` and `updated_at`. The `onUpdate` callback refreshes `updated_at` when JPA updates the entity. [Product callbacks](../../../src/main/java/com/example/productapi/domain/Product.java#L55) |
| Version | JPA uses `@Version` for optimistic locking. V4 adds a non-null `BIGINT` column with default `0`. The HTTP request and response DTOs omit this server-managed field. [Product.version](../../../src/main/java/com/example/productapi/domain/Product.java#L23) · [V4 migration](../../../src/main/resources/db/migration/V4__add_version_to_products.sql#L1) · [ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java#L6) |
| Seed records | Flyway V3 inserts initial catalog records. [V3 migration](../../../src/main/resources/db/migration/V3__seed_products.sql#L1) |

See [Update a product](features/update-product.md) for the client-visible outcome of overlapping writes.
