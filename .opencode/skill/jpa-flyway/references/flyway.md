# Flyway Reference

## When to load

Load when creating or editing database migrations.

## Naming

```
V{version}__{description}.sql
```

Examples:
- `V1__create_products.sql`
- `V2__add_price_column.sql`

## Rules

- Never edit an applied migration; add a new one.
- One migration per change.
- Version numbers ascend.

## Example

```sql
-- V1__create_products.sql
CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(19,2) NOT NULL,
    category_id BIGINT REFERENCES categories(id)
);
```

## Config

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
```
