---
name: docx-export
description: Use when exporting domain docs to .docx via Pandoc from Markdown. Triggers: "export doc", "docx", "word document".
---

# DOCX Export

## When to Use

- Export markdown -> .docx per domain

## Workflow

1. Ensure markdown follows the documentation templates.
2. Convert with Pandoc using the reference-doc template.
3. Output to `docs/api-docs/docx/{domain}.docx`.

## Command Pattern

```
pandoc input.md -o output.docx --reference-doc=ref.docx
```

Pandoc runs inside the dev container.

## References

| Topic | File |
|---|---|
| Pandoc usage + ref.docx | `references/docx-template.md` |
