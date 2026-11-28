---
name: spring-docs-structured-lite
description: Create, update, check, or translate a structured set of project and module documents across programming languages using existing documentation and bundled templates. Use only when the user explicitly selects spring-docs-structured-lite.
---

# Structured project documentation

For a check, return findings in chat. For a write (including an update or
translation), the first turn is read-only. Calling this skill chooses structured
documentation. Approval to write comes from the user's reply to the proposed tree.

1. Inspect the project instructions, root README, docs map, existing module pages
   and assigned entry points. List EVERY directory and file under assets/templates and compare those paths with
   the repository. Open template content during preview where it informs a
   planned action, including the root README and AGENTS decisions. A missing
   behavior needs only the template path to plan a zero-byte file.
   Inventory actions from the named entry point, such as a controller, route,
   event, consumer, command or job, and trace their outcomes before proposing
   feature pages. Use the source and configuration of the assigned module.
   For non-Spring code in scope, read only the applicable section of the
   [entry-point guide](references/entry-points.md). Defer detailed
   tracing and drafting until the user approves.
2. In the final chat response, show an indented directory tree. Mark each file
   create, update, reuse or replace, with one short description, in the user's language.
   Include root README and AGENTS when relevant. Briefly identify the snapshot,
   language, planned checks and a scope-based model/effort recommendation.
   Mention once that eligible pages will carry their source checkpoint.
   Ask whether the user approves this tree or wants changes, then **end the turn**.
   Leave every file untouched, including README, AGENTS and the preview itself.
3. If the user requests changes, show the revised tree and ask again in a
   read-only turn. Once the user approves the latest tree, apply its create, update and replacement entries
   and preserve its reuse entries. One approval covers those edits and
   checks. If another file needs editing, show the revised tree and wait for approval
   before adding that edit.

Show all seven project files: README.md, AGENTS.md, docs/README.md,
docs/ARCHITECTURE.md, docs/DEVELOPMENT.md, docs/TESTING.md and docs/CODING_RULES.md.
Use links from these canonical pages when material already lives elsewhere.

For each assigned module, show the complete template tree. Each slot maps to
real pages for the evidenced behavior, or to its literal template filename with
zero bytes when there is no behavior. This includes fixed index files in unused
sections. Stack detection does not add, remove or rename template slots. Mark empty files in the preview only. When a real page replaces a
placeholder, show both paths for approval. Inspect existing content before any
replacement and propose how to preserve user text.

Create one feature per independently meaningful action. A CRUD controller with
list, detail, create, update and delete gets five feature pages. Keep validation,
transactions and error paths with their action. For an event, follow publication,
listeners, recipients and side effects. Group handlers only when code demonstrates a
shared business purpose. Cite paths and symbols and explain the grouping in the
preview and feature. Surface unresolved groupings for review. Link technical
listener/consumer pages to the corresponding end-to-end feature.

When splitting an existing aggregate feature, keep its path as a short linked
index, move its substantive details to the action pages and update module OVERVIEW.md
with direct links to each. Show these changes together in the tree.
Read references by heading. Locate sections with `rg -n '^#{1,3} '` and read the
relevant ranges. Reuse evidence already in context while it remains current.

- Before preview, read **Inspect and preview**, **AGENTS before business docs**,
  **Account for every template path**, and the planning portions for the page
  types in scope in the [structured guide](references/structured-docs.md).
  Read **Establish the request and evidence snapshot** and the language defaults
  in the [evidence guide](references/evidence-and-language.md). Consult update,
  translation or reconciliation rules when those affect the proposed tree.
- After approval, read the applicable page guidance and templates for the
  approved edits, plus **Scope, rules and evidence** and **Review**. Follow
  concrete evidence gaps into source, configuration, tests and existing docs.
- For check or update, read **Determine documentation impact**. Read translation
  lineage for translation or changes affecting existing translations. Read legacy
  and after-merge guidance when handling those cases.

After approval, stamp each populated template page created from source or updated
and checked against source, including an index retained at an old feature path.
Skip README.md and AGENTS.md at every depth (case-insensitive) and leave empty
placeholders at zero bytes. Reused pages keep their existing checkpoint. Follow
the [per-page checkpoint rules](references/snapshot-metadata.md#per-page-source-checkpoint).
Check the final diff for coverage, exceptions and duplicate records.

Apply the [evidence and language rules](references/evidence-and-language.md) to source
claims, updates, and translations. A check returns findings in chat. For verification
records and translation identity, consult [snapshot metadata](references/snapshot-metadata.md).
Verify paths, navigation, claims against the selected source, and the final diff.
Report changed pages, checks performed, remaining gaps and any conflict between
the requested behavior and the source. Skill selection grants no extra permissions.
