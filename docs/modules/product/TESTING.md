# Product testing

Scope reviewed at `63ea63b435d84bf07ac311eff8e7483bce3135eb` includes Product queries, CRUD, JWT access, cache invalidation, and overlapping writes.

## Select and run checks

Run these commands in the devcontainer. Integration checks require an active Docker daemon reachable through the mounted socket. They use a separate PostgreSQL 16 Testcontainers database, Flyway, and a random HTTP port. [Project test setup](../../../README.md#test) · [Failsafe configuration](../../../pom.xml#L132) · [Integration setup](../../../src/test/java/com/example/productapi/integration/BaseIntegrationTest.java#L24)

| When changing | Command | Existing assertions |
| --- | --- | --- |
| Paging, sorting, filtering, or DTO mapping | `mvn -Dtest=ProductControllerTest,ProductSpecificationTest,ProductRepositoryTest,ProductServiceTest,ProductMapperTest test` | Default page and stable sort, invalid list input, combined filters on H2, DTO page metadata, and preservation of persistence metadata during mapping. [MVC](../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java#L65) · [Specification](../../../src/test/java/com/example/productapi/repository/spec/ProductSpecificationTest.java#L14) · [Repository](../../../src/test/java/com/example/productapi/repository/ProductRepositoryTest.java#L30) · [Service](../../../src/test/java/com/example/productapi/service/ProductServiceTest.java#L28) · [Mapper](../../../src/test/java/com/example/productapi/mapper/ProductMapperTest.java#L36) |
| Cache keys or invalidation | `mvn -Dtest=ProductCacheTest test` | Repeated detail reads reuse the DTO, a successful service update evicts it, and cache synchronization defers per-key eviction until commit. [Cache tests](../../../src/test/java/com/example/productapi/service/ProductCacheTest.java#L19) |
| HTTP CRUD, roles, or transactional conflicts | `mvn -Dit.test=ProductCrudIT,AuthFlowIT verify` | Create response includes a Location header, subsequent reads reflect update and delete, invalid input returns field errors, JWT roles gate reads and writes, and overlapping PUTs return one `200` and one `409`. Maven also runs the regular test phase. [CRUD integration](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java#L23) · [Create helper](../../../src/test/java/com/example/productapi/integration/BaseIntegrationTest.java#L69) · [Authorization integration](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java#L34) |

## Known gaps

- **Filtered results over HTTP on PostgreSQL.** The MVC tests exercise paging and sort inputs, and the repository tests exercise combined predicates on H2. The inspected Product and Auth integration tests do not assert filtered page contents through HTTP against PostgreSQL. [MVC](../../../src/test/java/com/example/productapi/controller/ProductControllerTest.java) · [Repository](../../../src/test/java/com/example/productapi/repository/ProductRepositoryTest.java) · [Product integration](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java) · [Auth integration](../../../src/test/java/com/example/productapi/integration/AuthFlowIT.java)
- **Cache contents after a real failed write.** The cache test simulates transaction synchronization for per-key eviction. The HTTP concurrency test checks response statuses. The reviewed tests do not assert cache contents after a real Product write rolls back. [Cache test](../../../src/test/java/com/example/productapi/service/ProductCacheTest.java#L41) · [Concurrency test](../../../src/test/java/com/example/productapi/integration/ProductCrudIT.java#L62)

[Product overview](OVERVIEW.md)
