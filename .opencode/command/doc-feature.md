---
description: Generate a feature doc in EN + VI.
agent: doc-writer
---

Generate feature docs for feature "$ARGUMENTS":

1. Load the `documentation` and `i18n-docs` skills.
2. Write `docs/domains/{domain}/features/{feature}.md` (EN) using the feature template.
3. Write `{feature}.vi.md` with `source`, `source-hash`, and `last-synced` metadata.

Determine the domain from the codebase; ask if ambiguous.
