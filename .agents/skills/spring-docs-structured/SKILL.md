---
name: spring-docs-structured
description: Create, update, check, or translate a structured set of Java/Spring project and module documents using existing documentation and bundled templates. Use when the user selects structured documentation or explicitly invokes this skill.
---

# Structured Spring documentation

For a check, return findings in chat. For a write (including an update or
translation), the first turn is read-only. Calling this skill chooses structured
documentation. Approval to write comes from the user's reply to the proposed tree.

1. Inspect the project instructions, root README, docs map, existing module pages
   and assigned entry points. Compare the repository with EVERY path under assets/templates.
   Inventory the controller operations or event handlers and trace their outcomes before proposing feature pages. Treat a named
   controller as the starting point for the requested behavior. Defer detailed
   tracing and drafting until the user approves.
2. In the final chat response, show an indented directory tree. Mark each file
   create, update, reuse or replace, with one short description, in the user's language.
   Include root README and AGENTS when relevant. Briefly identify the snapshot,
   language, planned checks and a scope-based model/effort recommendation.
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
sections. Mark empty files in the preview only. When a real page replaces a
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
Use the [structured documentation guide](references/structured-docs.md) for content.

Apply the [evidence and language rules](references/evidence-and-language.md) to source
claims, updates, and translations. A check returns findings in chat. For verification
records and translation identity, consult [snapshot metadata](references/snapshot-metadata.md).
Verify paths, navigation, claims against the selected source, and the final diff.
Report changed pages, checks performed, remaining gaps and any conflict between
the requested behavior and the source. Skill selection grants no extra permissions.
