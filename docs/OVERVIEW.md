# System Overview

## Purpose
Product Management backend với role-based auth.

## Architecture
Package-by-feature (layered trong mỗi feature).

## Domains

| Domain | Entities | Status |
|---|---|---|
| product | Product | In progress |
| user | User | In progress |

## Tech stack
Java 21, Spring Boot 4.0, Postgres 16, Flyway, JWT, Zipkin.

## External dependencies
(để trống hoặc điền sau)

## Observability
Actuator + Micrometer Tracing + Zipkin.