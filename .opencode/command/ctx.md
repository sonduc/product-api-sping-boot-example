---
description: Load and summarize domain context from docs/ before coding.
agent: backend-engineer
---

Load context for domain "$ARGUMENTS":

1. Run the `context-loader` skill.
2. Read `docs/domains/{domain}/` (README, data-model, srs).
3. Read related flow and feature docs.
4. Check freshness via `last-updated` and warn/ask as needed.
5. Summarize the domain context (entities, endpoints, business rules).

Do not write code yet. Present the summary and wait for confirmation.
