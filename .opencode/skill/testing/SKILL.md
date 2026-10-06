---
name: testing
description: Use when writing JUnit 5, Mockito, or Testcontainers tests, or checking JaCoCo coverage gates. Triggers: "test", "coverage", "mock", "integration test".
---

# Testing

## When to Use

- Unit test / integration test
- Mockito mock / stub
- Testcontainers
- JaCoCo coverage gates

## Gates

Line coverage >= 70%, branch coverage >= 60%. Run `mvn clean verify`.

## Quick Reference

| Concern | Convention |
|---|---|
| Unit | `@WebMvcTest` / `@ExtendWith(MockitoExtension.class)` |
| Integration | `@SpringBootTest` + Testcontainers (Postgres 16) |
| Coverage | JaCoCo, build fails under gate |
| Cases | always positive + negative |

## References

| Topic | File |
|---|---|
| Testcontainers setup + patterns | `references/testcontainers.md` |
