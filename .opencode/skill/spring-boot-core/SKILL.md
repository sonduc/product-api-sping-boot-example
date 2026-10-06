---
name: spring-boot-core
description: Use when creating REST endpoints, controllers, DTOs, validation, exception handling, or organizing package-by-feature. Triggers: "create endpoint", "REST", "controller", "validation", "exception".
---

# Spring Boot Core

## When to Use

- Create an endpoint, controller, or REST API
- DTO + validation
- Exception handling / error response format
- Package-by-feature layout

## Package-by-feature

```
src/main/java/{base}/
  {feature}/
    controller/
    service/
    repository/
    domain/    # entities
    dto/       # records (request/response)
    mapper/    # entity <-> dto
  common/
    exception/
    config/
```

## Quick Reference

| Concern | Convention |
|---|---|
| Controller | `@RestController` + `@RequestMapping("/api/v1/{feature}")` |
| Validation | `@Valid` + `jakarta.validation` annotations on DTO records |
| Errors | `@RestControllerAdvice` -> `ApiError` record |
| DTO | Java record with `@NotBlank`, `@DecimalMin`, etc. |
| Injection | constructor injection (final fields), no `@Autowired` |
| Response | DTO/record, never expose entity |

## MUST / MUST NOT

See AGENTS.md conventions. Key: constructor injection, DTO at boundary, no Lombok, global exception handler.

## References

| Topic | File |
|---|---|
| REST + validation + error response (full code) | `references/web.md` |
