# Project Documentation

Entry point for project docs. Copy this `docs-template/` to `docs/` when scaffolding a new project.

## Structure

- `OVERVIEW.md` (+ `.vi.md`) — system overview (bilingual)
- `domains/` — per-domain docs
- `background-jobs/` — cross-domain jobs (EN only)
- `onboarding/` — setup + workflow (bilingual) + conventions (EN)
- `api-docs/` — Postman collection + generated `.docx`

## Language Rules

- Song ngữ (EN + `.vi.md`): SRS, feature docs, OVERVIEW, onboarding.
- EN only: domain docs, flow charts, background jobs, conventions, API docs.
- Flow chart labels stay English.

## Per-domain layout

Each `domains/{domain}/` contains:

- `README.md` — domain overview (EN)
- `srs.md` + `srs.vi.md` — SRS (bilingual)
- `data-model.md` — entities + fields (EN)
- `api.md` — endpoints (EN)
- `jobs.md` — domain jobs (EN)
- `flows/{flow}.md` — Mermaid flows (EN labels)
- `features/{feature}.md` + `.vi.md` — feature docs (bilingual)
