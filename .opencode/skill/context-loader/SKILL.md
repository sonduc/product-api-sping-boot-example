---
name: context-loader
description: Use before coding to load domain context from docs/. Triggers: any build task touching an existing domain.
---

# Context Loader

## When to Use

- Any build task that touches an existing domain.

## Steps

1. Detect the domain from the task.
2. Read `docs/domains/{domain}/` (README, data-model, srs).
3. Read related flow and feature docs.
4. Check freshness via metadata `last-updated`:
   - < 30 days: OK.
   - 30-90 days: warn the user.
   - > 90 days: warn + ASK the user before coding.
5. Domain missing from `docs/domains/`: ASK the user before coding.
6. Summarize the loaded context, wait for confirmation, then code.

## Goal

Prevent comprehension debt: understand existing design before adding code.
