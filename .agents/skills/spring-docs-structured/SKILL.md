---
name: spring-docs-structured
description: Create, update, check, or translate a structured set of Java/Spring project and module documents using existing documentation and bundled templates. Use when the user selects structured documentation or explicitly invokes this skill.
---

# Structured Spring documentation

Calling this skill selects the structured workflow. Treat a named controller as a
starting point for tracing the requested behavior. Inspect the project instructions,
root README, documentation map and existing module pages. For a Product request,
locate the Product pages or their place in the map and trace the relevant controller
operations into their dependencies. A list of endpoints alone does not cover that task.

Use the [structured documentation guide](references/structured-docs.md) to select
pages, reuse existing paths, and handle README and AGENTS within the grant. Read
only the templates needed for the assigned behavior. Use job, event or RabbitMQ pages
only for relevant behavior evidenced in the source. An update within an existing
tree can be confined to one page.

Before writing, show a short proposed tree identifying what will be reused, updated
or created, the evidence snapshot, language and checks. Give a scope-based model and
effort recommendation for the planned work. Ask only about missing decisions that
materially affect the content or scope. A preview-only request ends in chat. For a
writing request, proceed within the grant after the preview. Preserve project-specific
instructions and user edits. Follow concrete dependencies without widening a narrow
request into whole-project documentation.

Apply the [evidence and language rules](references/evidence-and-language.md) to source
claims, updates, and translations. A check returns findings in chat. For verification
records and translation identity, consult [snapshot metadata](references/snapshot-metadata.md).
Verify paths, navigation, claims against the selected source, and the final diff.
Report changed pages, checks performed, remaining gaps and any conflict between
the requested behavior and the source. Skill selection grants no extra permissions.
