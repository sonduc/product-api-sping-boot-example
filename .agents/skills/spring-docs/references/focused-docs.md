# Focused documentation and existing-file updates

Use this route for a requested flow/controller explanation in a document, or a scoped
update to existing docs. It must be sufficient to work on the assigned behavior, not
merely a class inventory. A focused file does not require a new AGENTS or docs tree.

- Reuse the requested stable path. If none exists, propose one destination in the plan;
  `doc.md` is an example, not a mandatory name. Preserve existing organization.
- Trace assigned entry points through validation, authorization, business rules, state,
  persistence/transactions, errors and relevant external/background side effects.
  Document all assigned operations, not the entire containing module.
- Cover scope/completion, entry/input/output, rules, flow/branches/impacts, evidence
  and unknowns. Place code/config/test references beside material claims. Use a chart
  or table appropriate to the flow; do not mandate a diagram for every explanation.
- Existing-document updates retain its useful structure and metadata convention;
  the [feature template](../assets/templates/docs/modules/_module/features/_feature.md)
  is a content aid for new material, not permission to rewrite an existing format.
- If a dependency leads to a job/listener/consumer, explain or link the relevant effects;
  do not silently expand the deliverable into a structured project tree.
- Apply [evidence and language rules](evidence-and-language.md). Check-only returns
  findings in chat; updates/translations only change approved files.
