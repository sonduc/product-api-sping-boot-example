---
name: security-jwt
description: Use when implementing JWT stateless auth, the Spring Security filter chain, or method-level authorization. Triggers: "auth", "jwt", "login", "role", "@PreAuthorize".
---

# Security JWT

## When to Use

- Auth / login / register
- JWT token generation and validation
- Spring Security configuration
- `@PreAuthorize` / role-based access

## Quick Reference

| Concern | Convention |
|---|---|
| Auth | stateless JWT, no session |
| Filter | `OncePerRequestFilter` validates the token |
| Password | BCrypt |
| Roles | `@PreAuthorize("hasRole('...')")` |
| Secrets | from env vars, never in source |

## References

| Topic | File |
|---|---|
| Full flow + filter + `@PreAuthorize` | `references/jwt.md` |
