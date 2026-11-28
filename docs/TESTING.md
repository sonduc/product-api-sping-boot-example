# Testing

## Project-wide checks

Run Maven from the devcontainer. Use `mvn test` for unit, MVC and H2 repository tests. Use `mvn verify` to include Failsafe integration tests, including HTTP tests against PostgreSQL 16 via Testcontainers. Integration tests require a running Docker daemon accessible from the devcontainer. The shared integration harness starts a random-port HTTP server and supplies the container datasource. [README test instructions](../README.md#test), [Maven build](../pom.xml), [integration harness](../src/test/java/com/example/productapi/integration/BaseIntegrationTest.java).

## By module

- [Product testing](modules/product/TESTING.md) covers filtering, authorization, CRUD, cache behavior and concurrent updates.

<!-- spring-docs:verification:start -->
## Verification record

- `document_status: draft`
- `branch: feat/apply-codex-kit-ver4`
- `commit_id: 6d32fa9f430bec67eab94c911a41dfe6cd1e8a55`
- `checked_at: 2026-11-28T10:33:40+07:00`
- `code_review: unknown`
- `verification_status:` Compared the documented scope with source and applicable configuration or test assertions. Runtime tests were not run.
<!-- spring-docs:verification:end -->
