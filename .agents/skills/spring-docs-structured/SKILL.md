---
name: spring-docs-structured
description: Create, update, check, or translate a structured set of Java/Spring project and module documents using existing documentation and bundled templates. Use when the user selects structured documentation or explicitly invokes this skill.
---

# Structured Spring documentation

For a check, return findings in chat. For a write (including an update or
translation), the first turn is read-only. Calling this skill chooses structured
documentation. Approval to write comes from the user's reply to the proposed tree.

1. Inspect the project instructions, root README, docs map, existing module pages
   and assigned entry points just far enough to propose destinations. Treat a named
   controller as the starting point for the requested behavior. Defer detailed
   tracing and drafting until the user approves.
2. In the final chat response, show an indented directory tree. Mark each file
   create, update or reuse, with one short description, in the user's language.
   Include root README and AGENTS when relevant. Briefly identify the snapshot,
   language, planned checks and a scope-based model/effort recommendation.
   Ask whether the user approves this tree or wants changes, then **end the turn**.
   Leave every file untouched, including README, AGENTS and the preview itself.
3. If the user requests changes, show the revised tree and ask again in a
   read-only turn. Once the user approves the latest tree, write its create/update
   entries and preserve its reuse entries. One approval covers those edits and
   checks. If another file needs editing, show the revised tree and wait for approval
   before adding that edit.

Use the [structured documentation guide](references/structured-docs.md) for page
selection and project conventions. Read only the templates needed. Follow concrete
dependencies within scope and include job, event or RabbitMQ pages only when relevant.
An update to an existing tree may cover a single page.

Apply the [evidence and language rules](references/evidence-and-language.md) to source
claims, updates, and translations. A check returns findings in chat. For verification
records and translation identity, consult [snapshot metadata](references/snapshot-metadata.md).
Verify paths, navigation, claims against the selected source, and the final diff.
Report changed pages, checks performed, remaining gaps and any conflict between
the requested behavior and the source. Skill selection grants no extra permissions.
