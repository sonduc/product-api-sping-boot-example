---
name: documentation
description: Use when generating feature docs, domain docs, SRS, flow charts (Mermaid), or background job docs. Triggers: "write doc", "SRS", "flow chart", "feature doc".
---

# Documentation

## When to Use

- Feature doc / domain doc / SRS / flow chart / background job doc

## Docs Structure

```
docs/
  README.md + .vi.md           # entry (bilingual)
  OVERVIEW.md + .vi.md         # system overview (bilingual)
  domains/
    README.md                  # domain index (EN only)
    {domain}/
      README.md                # domain overview (EN only)
      srs.md + srs.vi.md       # SRS (bilingual)
      data-model.md            # EN only
      api.md                   # EN only
      jobs.md                  # domain jobs (EN only)
      flows/{flow}.md          # Mermaid, EN labels only
      features/{feature}.md + .vi.md  # feature doc (bilingual)
  background-jobs/             # cross-domain jobs (EN only)
  onboarding/                  # setup + workflow (bilingual), conventions (EN)
  api-docs/
    postman-collection.json    # EN only
    docx/{domain}.docx         # generated per domain
```

## Language Rules

- Song ngữ (EN + .vi.md): SRS, feature docs, OVERVIEW, onboarding.
- EN only: domain docs, flow charts, background jobs, conventions, API docs.

## Templates

| Doc | Template |
|---|---|
| Feature | `references/feature-template.md` |
| SRS | `references/srs-template.md` |
| Flow | `references/flow-template.md` |
