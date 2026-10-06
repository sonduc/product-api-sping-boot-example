---
description: Scaffold a new entity plus repository, service, controller, and tests.
agent: backend-engineer
---

Scaffold a new feature for entity "$ARGUMENTS":

1. Run the `context-loader` and `explain-before-code` skills first.
2. Create package-by-feature: domain entity, repository, service, DTO records, controller.
3. Create a Flyway migration `V{n}__create_{table}.sql`.
4. Create unit tests (positive + negative).

Return the reasoning block (What/Why/Trade-offs/Risks/Concepts) before writing files.
