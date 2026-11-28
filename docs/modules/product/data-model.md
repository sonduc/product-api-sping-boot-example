# Product data model

## Main data

[Product](../../../src/main/java/com/example/productapi/domain/Product.java) maps to the `products` table.

| Field | Meaning and storage |
| --- | --- |
| `id` | Generated identity and primary key |
| `name`, `description` | Required name up to 255 characters and optional text description |
| `price`, `stock` | Required decimal amount with precision 12 and scale 2, and integer quantity |
| `createdAt`, `updatedAt` | Required timestamps initialized by `onCreate`. `onUpdate` refreshes the update timestamp on an entity update. |
| `version` | Required integer version stored as BIGINT and managed by JPA optimistic locking |

The table is defined in [V1](../../../src/main/resources/db/migration/V1__init_schema.sql). [V4](../../../src/main/resources/db/migration/V4__add_version_to_products.sql) adds the version column.

## Data relationships

The mapped product fields are scalar. The Product entity declares no JPA associations.

## Structural notes

The API exposes timestamps through the response DTO and keeps the persistence version internal. Request-level price and stock constraints are declared on `ProductRequest`. [ProductResponse](../../../src/main/java/com/example/productapi/dto/ProductResponse.java), [ProductRequest](../../../src/main/java/com/example/productapi/dto/ProductRequest.java).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
