# Product CRUD

## Scope

Clients can list and read products. Administrators can also create, replace editable fields, or delete a product. A successful write completes its database transaction before the controller returns. [Controller](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L26) · [Transactional service](../../../../src/main/java/com/example/productapi/service/ProductService.java#L41)

## Entry points

All five operations enter through `ProductController` under `/api/products`. The [API map](../api.md) lists methods, roles, DTOs, and HTTP outcomes.

## Rules

| Input | Applied rule | Evidence |
| --- | --- | --- |
| `page`, `size` | Zero-based pages, default `0`. Size defaults to `10` and must be in `1–100`. | [getAll](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) |
| `sort` | Default `createdAt,desc`. Supply one field and an `asc` or `desc` direction. Allowed fields are `id`, `name`, `price`, `stock`, `createdAt`, and `updatedAt`. Every sort on a field other than `id` adds `id ASC` as a tie-breaker. | [Allowed fields](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L30) · [parseSort](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L56) |
| `name` | Trimmed, case-insensitive substring match. Blank input omits this filter. The query treats `%`, `_`, and `!` as literal characters in the search term. | [hasName](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L15) |
| `minPrice`, `maxPrice` | Both bounds are inclusive and optional. Negative or reversed bounds return `400`. | [priceBetween](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L26) · [Business error handler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L47) |
| `inStock` | `true` selects stock greater than zero, `false` selects stock equal to zero, and omission leaves stock unrestricted. Supplied filters are combined with AND. | [inStock and build](../../../../src/main/java/com/example/productapi/repository/spec/ProductSpecification.java#L40) |
| Create and update body | A nonblank name of at most 255 characters, a positive price with at most 10 integer digits and 2 decimal places, and a nonnegative integer stock are required. Description is optional. PUT replaces all four editable fields, including setting description to null if the request omits it. | [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [updateEntity](../../../../src/main/java/com/example/productapi/mapper/ProductMapper.java#L27) |

## Flow and effects

| Operation | Processing |
| --- | --- |
| List | The controller builds a page request. In a read-only transaction, the service queries by specification and maps the page to DTOs. The controller wraps page metadata and returns it to the client. [Controller](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L39) · [Service](../../../../src/main/java/com/example/productapi/service/ProductService.java#L29) |
| Read by ID | A cache hit returns the DTO for that ID. A miss loads and maps the product and caches the result. A missing ID returns `404`. [Read service](../../../../src/main/java/com/example/productapi/service/ProductService.java#L35) · [Lookup](../../../../src/main/java/com/example/productapi/service/ProductService.java#L64) |
| Create | Map the body to a new entity, save and flush inside the transaction, then return the mapped result with `201` and `Location`. [Service](../../../../src/main/java/com/example/productapi/service/ProductService.java#L43) · [Controller](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L76) |
| Update | Load by ID, apply the supplied fields, save and flush, then return the mapped product with `200`. A missing ID returns `404`. [Service](../../../../src/main/java/com/example/productapi/service/ProductService.java#L49) · [Controller](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L83) |
| Delete | Load by ID, delete and flush, then return `204`. A missing ID returns `404`. [Service](../../../../src/main/java/com/example/productapi/service/ProductService.java#L57) · [Controller](../../../../src/main/java/com/example/productapi/controller/ProductController.java#L90) |

The in-memory cache is local to the app instance and has no configured TTL. Only detail DTOs are cached. Each successful write clears all entries after commit. A rolled-back write leaves existing entries in place. [Cache config](../../../../src/main/java/com/example/productapi/config/CacheConfig.java#L14) · [Cache annotations and transactions](../../../../src/main/java/com/example/productapi/service/ProductService.java#L29)

JPA checks the entity version when it writes. When two updates read the same version and one commits first, an optimistic locking failure rolls back the losing write and returns `409`. The request has no version field, so an older form submitted after the earlier transaction has committed can overwrite the current values. [Product.version](../../../../src/main/java/com/example/productapi/domain/Product.java#L23) · [Conflict handler](../../../../src/main/java/com/example/productapi/exception/GlobalExceptionHandler.java#L65) · [ProductRequest](../../../../src/main/java/com/example/productapi/dto/ProductRequest.java#L11) · [Concurrency test](../../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java#L62)

## Verification

See [Product testing](../TESTING.md) for existing assertions, commands, and coverage gaps.
