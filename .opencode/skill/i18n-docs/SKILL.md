---
name: i18n-docs
description: Use when creating or syncing Vietnamese translations (.vi.md) with source-hash metadata. Triggers: "translate", "sync doc", "tiếng Việt".
---

# i18n Docs

## Language Rules

- EN is default; Vietnamese goes in `.vi.md`.
- Song ngữ (EN + .vi.md): SRS, feature docs, OVERVIEW, onboarding.
- EN only: domain docs, flow charts, background jobs, conventions, API docs.

## Metadata (frontmatter of every .vi.md)

```
---
source: README.md
source-hash: <hash>
last-synced: YYYY-MM-DD
---
```

## Sync Logic

1. Read `source-hash` from the `.vi.md` file.
2. Hash the current EN source file.
3. Mismatch -> re-translate the EN content.
4. VI file missing -> create it.
5. EN missing but VI present -> warn (rare).
