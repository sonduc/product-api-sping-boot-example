---
name: jpa-flyway
description: Use when defining entities, repositories, queries, transactions, or Flyway migrations. Triggers: "entity", "repository", "N+1", "migration", "JPA".
---

# JPA + Flyway

## When to Use

- Entity / repository / query
- N+1 problem, lazy loading, transaction management
- Database migration / schema change

## Quick Reference

| Concern | Convention |
|---|---|
| Entity | `@Entity @Table`, `GenerationType.IDENTITY` |
| Repository | `interface ... extends JpaRepository<...>` |
| Reads | `@Transactional(readOnly = true)` |
| Writes | `@Transactional` |
| Fetch | default LAZY for `@ManyToOne`/`@OneToOne` |
| Migration | `V{n}__{desc}.sql`, never edit applied |

## References

| Topic | File |
|---|---|
| JPA patterns + N+1 prevention | `references/data.md` |
| Flyway conventions | `references/flyway.md` |
