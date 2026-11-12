# Coding rules

## 1. Naming and code conventions
| Object | Convention | Example / evidence |
|---|---|---|
| Package, class, interface | {...} | {...} |
| Method, variable, constant | {...} | {...} |
| DTO, mapper, configuration | {...} | {...} |

- {Injection, existing libraries and utilities.}
- {Logging, comments and configuration.}
- Format with {existing formatter/config}.
- File placement and layer responsibilities: {architecture document link}.

## 2. Error handling
- {Business/technical errors; exception types, error codes and shared handlers.}
- {Where to catch, translate or propagate errors.}
- {Caller-visible information, logging, cause preservation and duplicate-log prevention.}
- Evidence/examples: {source links}.

## 3. API
- {Paths, methods, statuses, request/response DTOs, mapping and serialization.}
- {Input validation, errors, pagination, sorting and filtering where applicable.}
- {Contract compatibility and API specification source workflow.}
- Endpoint details: {module API docs links}.

## 4. Data and database
- {Mapping, data types and database naming.}
- {Migration location/naming, creation and limits on applied migrations.}
- {Transaction boundaries and rollback.}
- {Queries, pagination, constraints, indexes and concurrency mechanisms.}
- Schema/relationships: {module data-model links}.

## 5. Security and authorization
- {Endpoint protection and explicit public endpoints.}
- {Permission checks and owner/tenant/branch scope.}
- {Trusted identity/tenant sources; internal and background callers.}
- {Secrets and sensitive-data handling.}
- Business-specific permissions and conditions: {feature docs links}.

## 6. Background processing and integrations
- {Existing job/event/listener/message conventions and transaction timing.}
- {Retry, idempotency, partial failure, external timeout/error behavior.}
- {Ack/nack and error routing if applicable; cache keys/TTL/invalidation if used.}
- Flow details: {job/listener/consumer links}.

## 7. Exceptions and unresolved rules
| Rule | Exception / legacy area | Treatment |
|---|---|---|
| {...} | {...} | {...} |

{Separate established conventions, isolated implementation and proposed rules.
Omit unused mechanisms; do not elevate a single unexplained example into a mandate.}
