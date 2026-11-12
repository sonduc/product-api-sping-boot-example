# Structured documentation

## Inspect and preview

Inspect project instructions, build/wrappers/CI, high-level package layout, representative
conventions and assigned entry points. Gather enough project context for AGENTS and
shared docs without exploring every business flow. Read further on concrete evidence gaps.

Preview the proposed output tree before writing: scope, new/reused/updated files,
existing conventions, old-doc treatment and checks. Preserve stable paths for updates;
restructuring requires coverage in the plan/grant. The preview is part of planning,
not another approval round when the grant already covers completion.

## AGENTS before business docs

Use the [AGENTS template](../assets/templates/AGENTS.md) to propose project-specific
reading routes, workflow, checks, rights and handoff. Reuse a sufficient existing
AGENTS; merge only authorized gaps, preserving instructions and local overrides.
Do not replace it wholesale, broaden rights or require matching headings. `context.md`
means AGENTS here; do not create a second instruction file. Template permission rules
describe boundaries, not authorization for the current task. If AGENTS edits are excluded,
reuse a sufficient existing file or report the missing scope before dependent work.

Complete/reuse this context before writing business docs. Survey the assigned behavior
deeply only after that; a narrow controller request does not authorize whole-module docs.

## Select templates, not an obligatory file tree

Templates are output assets, not instructions to load together. Read only the chosen
templates; replace or omit placeholders using actual project evidence. The English
template wording does not change [language rules](evidence-and-language.md).

Shared documents, created only when useful and authorized:

| Template | Responsibility |
|---|---|
| [Root README](../assets/templates/README.md) | Purpose, relevant stack/version sources, starting links |
| [Docs map](../assets/templates/docs/README.md) | Shared-doc links and module overview map |
| [Architecture](../assets/templates/docs/ARCHITECTURE.md) | Actual structure, layer/module responsibilities and dependency rules |
| [Coding rules](../assets/templates/docs/CODING_RULES.md) | Naming/errors/API/data/security/background rules supported by project |
| [Development](../assets/templates/docs/DEVELOPMENT.md) | Prerequisites, configuration, initialization, run/stop and expected signals |
| [Testing](../assets/templates/docs/TESTING.md) | Existing checks, commands, selection, prerequisites and evidence reporting |

Keep one setup source; if README already has it, link instead of duplicating DEVELOPMENT.
Retain existing paths rather than forcing uppercase names. Stack versions must come from
build/config/wrappers; distinguish declared/resolved/local versions and unknowns. Do not
list every library or run dependency resolution merely to populate a table.

Module templates under `docs/modules/_module/` (rename `_module`, `_feature`, `_verb`):

| Template | Responsibility |
|---|---|
| [OVERVIEW](../assets/templates/docs/modules/_module/OVERVIEW.md) | Responsibilities, features, components, relationships |
| [API](../assets/templates/docs/modules/_module/api.md) | Controller/API map with DTO/code references, not exhaustive payload specs |
| [Data model](../assets/templates/docs/modules/_module/data-model.md) | Main data, relationships, structural notes |
| [Feature](../assets/templates/docs/modules/_module/features/_feature.md) | Five sections: scope, entry, rules, flow/impact, verification/gaps |
| [Action job](../assets/templates/docs/modules/_module/jobs/_verb-action.md) / [cron job](../assets/templates/docs/modules/_module/jobs/_verb-cron.md) | Four sections: description, trigger, flow, errors |
| [Events map](../assets/templates/docs/modules/_module/events/README.md) / [listener](../assets/templates/docs/modules/_module/events/listeners/_verb.md) | Published/received events and individual listener behavior |
| [RabbitMQ map](../assets/templates/docs/modules/_module/rabbitmq/README.md) / [consumer](../assets/templates/docs/modules/_module/rabbitmq/consumers/_verb.md) | Message topology and individual consumer behavior |

Do not create empty job/event/RabbitMQ docs. Do not create a modules-level OVERVIEW,
module-root README, separate flows directory, or default SRS. No forced EN/VI pairs,
onboarding/Postman/DOCX or other deferred deliverables.

## Scope, rules and evidence

Label partial coverage in overviews/maps and counts: selected create/update flows are
not the entire module. Follow relevant callers, security matchers/config, persistence,
transaction timing, jobs/events/messages and failure paths. Event existence does not
prove async behavior; topology does not prove delivery guarantees. Avoid duplicating
payloads/routing in listeners/consumers; link to the source event/message map.

Keep technical facts/rules in shared docs, behavior in module docs, reading/workflow in
AGENTS. Distinguish established convention, isolated implementation, proposed rule and
unknown; never turn a sample stack/layer or an unexplained legacy pattern into a mandate.
Material claims need nearby path/line plus symbol/config evidence from the selected snapshot.

## Review

Check the generated navigation, source references and diagram steps against the assigned
scope. Remove placeholders and links to omitted files. Check AGENTS links point to the
actual project paths. Record checks/limits truthfully; TESTING describes how to verify,
not a fabricated passing run. Recheck source bytes for concurrent edits before handoff.
