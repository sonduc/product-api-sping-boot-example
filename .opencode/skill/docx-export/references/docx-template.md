# DOCX Export Reference

## When to load

Load to convert domain markdown to .docx.

## Pandoc

```bash
pandoc input.md -o output.docx --reference-doc=ref.docx
```

## Workflow

1. Ensure markdown follows the documentation templates.
2. Run Pandoc with `--reference-doc` for styling.
3. Output to `docs/api-docs/docx/{domain}.docx`.

## Notes

- `ref.docx` defines styles (headings, fonts, margins).
- Batch over the `docs/domains/` folder.
- Pandoc runs inside the dev container.
