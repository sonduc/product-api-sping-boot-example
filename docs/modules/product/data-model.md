# Product data model

`Product` maps to the PostgreSQL `products` table. It stores scalar catalog fields and has no mapped relationships or foreign keys. [Product entity](../../../src/main/java/com/example/productapi/domain/Product.java#L15) · [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8)

| Field | Storage and lifecycle | Evidence |
| --- | --- | --- |
| `id` | `BIGSERIAL` primary key, mapped with JPA identity generation | [Product.id](../../../src/main/java/com/example/productapi/domain/Product.java#L19) · [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8) |
| `name`, `description` | Required `VARCHAR(255)` name and nullable `TEXT` description | [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8) |
| `price`, `stock` | Required `NUMERIC(12,2)` and `INT`. The API enforces a positive price and nonnegative stock. | [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8) · [Request validation](../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) |
| `created_at`, `updated_at` | Required `TIMESTAMP` columns. `@PrePersist` initializes both from the same `LocalDateTime`. `@PreUpdate` refreshes `updatedAt` when JPA updates the row. | [V1 schema](../../../src/main/resources/db/migration/V1__init_schema.sql#L8) · [Product callbacks](../../../src/main/java/com/example/productapi/domain/Product.java#L55) |
| `version` | Required `BIGINT`, added with a default of zero in V4. JPA uses `@Version` for optimistic locking. | [V4 migration](../../../src/main/resources/db/migration/V4__add_version_to_products.sql#L1) · [Product.version](../../../src/main/java/com/example/productapi/domain/Product.java#L23) |

Flyway manages the schema and Hibernate validates the mappings. [Application config](../../../src/main/resources/application.yml#L12)

[Product overview](OVERVIEW.md) · [CRUD transaction behavior](features/crud.md)
