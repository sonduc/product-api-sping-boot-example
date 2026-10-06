---
name: explain-before-code
description: Use before any non-trivial build task to force the agent to explain its approach first. Triggers: "implement", "build", "create feature".
---

# Explain Before Code

## When to Use

- Any build task: feature, entity, refactor.

## Mandatory Output (BEFORE code)

```
## What I did
## Why (reasoning + alternatives rejected)
## Trade-offs
## What could go wrong
## Concepts used
```

## Rules

- Emit the reasoning block first, then write code.
- For complex or security-sensitive logic, wait for user confirmation before writing.
- Keep reasoning concise; no essays.
