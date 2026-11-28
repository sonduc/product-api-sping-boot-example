# Product testing

Scope covers the five Product HTTP operations at source commit `6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`. Run the commands from the devcontainer. [Shared prerequisites](../../TESTING.md).

## Select and run checks

| When changing | Command | Existing assertions |
| --- | --- | --- |
| List binding, defaults and query rules | `mvn -Dtest=ProductControllerTest,ProductServiceTest,ProductSpecificationTest,ProductRepositoryTest test` | Default paging and stable sort, invalid parameters, invalid price ranges, filter predicates and combined H2 queries. [MVC](../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java), [service](../../../src/test/java/com/example/productapi/service/ProductServiceTest.java), [specification](../../../src/test/java/com/example/productapi/repository/spec/ProductSpecificationTest.java), [repository](../../../src/test/java/com/example/productapi/repository/ProductRepositoryTest.java). |
| Product mapping and cache | `mvn -Dtest=ProductMapperTest,ProductCacheTest test` | Editable field mapping, preservation of persistence metadata, detail DTO caching and cache behavior on commit and rollback. [Mapper](../../../src/test/java/com/example/productapi/mapper/ProductMapperTest.java), [cache](../../../src/test/java/com/example/productapi/service/ProductCacheTest.java). |
| CRUD, validation, cache and concurrent updates | `mvn -Dit.test=ProductCrudIT verify` with Docker | 201 with a Location header, read and update results, refreshed detail after update, 204 on delete, 404 on a subsequent read, POST field errors, and one 200 plus one 409 for overlapping updates. [CRUD HTTP tests](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java), [create helper](../../../src/test/java/com/example/productapi/integration/BaseIntegrationTest.java). |
| Read and write authorization | `mvn -Dit.test=AuthFlowIT verify` with Docker | Anonymous and malformed-token reads return 401, USER can read and receives 403 for writes, and ADMIN can perform CRUD. [Authorization HTTP tests](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java). |

## Known gaps

- **Filtered list over PostgreSQL HTTP.** The reviewed HTTP integration tests cover list access, while combined filter result assertions are in the H2 repository suite. [AuthFlowIT](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java), [ProductCrudIT](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java), [ProductRepositoryTest](../../../src/test/java/com/example/productapi/repository/ProductRepositoryTest.java).
- **Missing ID on write.** The reviewed CRUD HTTP tests assert 404 on GET after deletion. PUT and DELETE for an absent ID have no dedicated HTTP assertions in these suites. [ProductCrudIT](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java), [AuthFlowIT](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java).

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
