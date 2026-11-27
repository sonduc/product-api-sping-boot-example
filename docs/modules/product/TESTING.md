# Product testing

Scope reviewed at `8a3c58ce0bcf35cd37b2499356d0552fb50ae08f` includes all five Product HTTP operations and their supporting rules.

## Select and run checks

Run Maven commands in the devcontainer. The `verify` command below needs Docker for PostgreSQL Testcontainers. [Project testing guide](../../TESTING.md) · [BaseIntegrationTest](../../../src/test/java/com/example/productapi/integration/BaseIntegrationTest.java#L25)

| When changing | Command | Observable assertions |
| --- | --- | --- |
| List input or query | `mvn -Dtest=ProductControllerTest,ProductServiceTest,ProductSpecificationTest,ProductRepositoryTest test` | Default page and stable sort, invalid page size and sort, DTO page metadata, filter validation, and combined predicates against H2. [Controller](../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java#L65) · [Service](../../../src/test/java/com/example/productapi/service/ProductServiceTest.java#L28) · [Specification](../../../src/test/java/com/example/productapi/repository/spec/ProductSpecificationTest.java#L24) · [Repository](../../../src/test/java/com/example/productapi/repository/ProductRepositoryTest.java#L30) |
| Detail DTO or write eviction | `mvn -Dtest=ProductMapperTest,ProductCacheTest test` | Entity-to-DTO mapping, repeated reads returning the cached DTO, successful update clearing it, and commit/rollback effects on cache clearing. [Mapper](../../../src/test/java/com/example/productapi/mapper/ProductMapperTest.java) · [Cache](../../../src/test/java/com/example/productapi/service/ProductCacheTest.java#L19) |
| HTTP create, detail, update, delete, or access rules | `mvn -Dit.test=ProductCrudIT,AuthFlowIT verify` | Creation returns 201 and a Location header. Details reflect updates and return 404 after deletion. Invalid create returns field errors, delete returns 204, and overlapping updates yield one 200 and one 409. Role checks verify USER reads and forbidden writes, plus ADMIN access. [CRUD flow](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java#L23) · [Create assertions](../../../src/test/java/com/example/productapi/integration/BaseIntegrationTest.java#L69) · [Access rules](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java#L34) |

## Known gaps

- **Filtered list through PostgreSQL.** List-level assertions use mocked MVC inputs or an H2 repository. PostgreSQL HTTP tests assert list access but do not exercise combined filters with pagination and sorting. [MVC list cases](../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java#L65) · [H2 repository cases](../../../src/test/java/com/example/productapi/repository/ProductRepositoryTest.java#L18) · [HTTP access cases](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java#L42)
- **Missing IDs on writes.** The HTTP missing-product test asserts 404 for GET. The reviewed Product MVC and integration tests contain no 404 assertion for PUT or DELETE. [MVC cases](../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java) · [CRUD cases](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java#L52) · [Access cases](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java#L42)
