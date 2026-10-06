---
description: Check EN<->VI hash sync and re-translate stale docs.
agent: doc-writer
---

Sync `docs/` translations:

1. Load the `i18n-docs` skill.
2. Scan all `.vi.md` files; read `source-hash` and compare with the current EN file hash.
3. Re-translate stale VI files; create missing VI files.
4. Warn if an EN file is missing but a VI file exists.
