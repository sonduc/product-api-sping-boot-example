# Structured documentation

## Inspect and preview

Inspect project instructions, build/wrappers/CI, high-level package layout, representative
conventions and assigned entry points. Gather enough project context for AGENTS and
shared docs without exploring every business flow. Read further on concrete evidence gaps.

Show the user the proposed directory tree with an action and purpose for each file.
End the read-only turn by asking them to approve or revise it. Apply the writing
guidance below after they approve the latest tree. Preserve stable paths for updates.

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
| [Architecture](../assets/templates/docs/ARCHITECTURE.md) | Code layout, representative processing flow and consequential constraints |
| [Coding rules](../assets/templates/docs/CODING_RULES.md) | Project conventions that guide coding and review |
| [Development](../assets/templates/docs/DEVELOPMENT.md) | Prerequisites, configuration, initialization, run/stop and expected signals |
| [Testing](../assets/templates/docs/TESTING.md) | Project-wide checks and links to module testing guides |

Retain existing paths rather than forcing uppercase names. Stack versions must come from
build/config/wrappers; distinguish declared/resolved/local versions and unknowns. Do not
list every library or run dependency resolution merely to populate a table.

### Review the root README in the plan

Compare the root README with the [template](../assets/templates/README.md) even when
the file already exists. Check what it covers, not its heading names or formatting.

- **Technology:** key technologies and relevant versions with links to their build,
  wrapper or configuration sources. An existing Stack section can cover this.
- **Start here:** short navigation to applicable setup, architecture, tests, the docs
  map and AGENTS. Use existing sections or files as destinations. Keep detailed
  module and feature lists in the docs map or module docs.

In the preview, identify what is already covered, what is missing, and whether the
README will be reused, updated, or created. Reuse an adequate README unchanged.
When the grant covers README, fill only the applicable gaps in place, preserving
existing content, language and setup instructions. If the file is absent and creation
is in scope, adapt the template. If README is outside the grant, list the applicable
gaps as suggestions in the plan and continue the authorized work. Omit unavailable
destinations rather than creating extra guides to fill the navigation.

### Plan and write project architecture

In the structured preview, check README sections and existing architecture docs
for the code layout, component responsibilities and an end-to-end processing flow.
Use the content needed for the request as the criterion, even when the entry point
is a controller or feature. Reuse adequate sections or pages and link to them.
For a small gap, propose filling it in place and update only within the grant.
When the project-level map is needed and the existing material does not cover it,
propose `docs/ARCHITECTURE.md` and state the gap in the preview. Create it under
the current grant when it covers new shared documentation, even if the prompt did
not name the file. If the user explicitly requests the page, honor that request
within scope and link to existing detail where it helps.

Build a short tree and location/responsibility/example table from the actual code.
Choose a representative flow to show its entry point, components, dependency
direction and observable completion. For synchronous HTTP, show the response back
to the client. If HTTP accepts work for background processing, distinguish the
HTTP response from the background outcome. For a scheduled job or consumer, use
its real trigger and final effect. Use a simple arrow line or short Mermaid diagram
as appropriate. Mention the flow used as evidence in a brief note and link to the
feature page for behavior details. Limit claims to the pattern the source supports.
Use two core sections: code structure and main processing flow. Link to stack,
setup, and feature details in their respective docs. Explain boundaries beside
the relevant step. Add a short constraints section only for source-backed
consequences for multiple flows or deployment. Keep verification metadata in
the project's established location or use a short evidence note in this page.

### Plan and write coding rules

Check relevant contribution guides, README sections and existing coding docs.
Reuse and link adequate conventions at their current paths. For a small gap,
propose an in-place update within the grant only for a missing established rule.
Cover new feature behavior in the feature page. If useful conventions lack a
suitable home, include `docs/CODING_RULES.md` in the structured preview and create
it when the grant covers new shared docs, even if the prompt did not name the file.
Honor an explicit page request within scope and link to existing detail where helpful.

Use naming, errors, APIs, data, security, cache and background work as survey
topics. Keep actionable rules supported by explicit guidance, a shared mechanism,
or consistent relevant examples. Limit each rule to the scope of its evidence.
Keep isolated implementation choices, endpoint details and behavioral differences
in feature docs. Keep the page to a one-line scope and populated rule groups.
Prefer one actionable sentence per rule with a shared line of short source links
per topic. Use a table when it helps compare several naming conventions. Put an
exception or unknown beside its rule only when it affects how to apply that rule.
Keep walkthroughs, reading instructions and verification metadata in the relevant
feature or shared docs. A few verified rules are enough for a short page. If none
can be verified, report the evidence gap in the preview.

### Plan and write local development onboarding

Include local onboarding in the structured preview even when the business scope starts
at a controller. Reuse a dedicated guide if adequate, propose updating it in place if
incomplete, or propose `docs/DEVELOPMENT.md` if none exists. Create/update only within the
grant. If README is in scope, link to the guide from Start here. Disclose any proposed
move of existing setup text and keep one maintained procedure when that move is in scope.

Consolidate README, existing docs, container configuration, samples and scripts into an
ordered path from clone to a running local app. Where setup instructions are incomplete,
still write a best-effort procedure from repository evidence such as build plugins,
wrappers, manifest scripts or declared configuration. Identify the component to run
and its language, runtime and build tool before choosing a manifest: for example,
`pom.xml` for Java/Spring with Maven, `package.json` for a JavaScript or TypeScript
component using Node.js, and `composer.json` for a PHP component using Composer. In
a multi-stack repo, check which component owns each manifest. Include commands for
another component only when repository evidence shows it is needed for the local setup in scope.
Place the source and a short “not run this way” note beside each inferred
step or command, in the document's language. State missing prerequisites beside the
affected step. When the evidence does not support a runnable command, identify what the
reader needs to confirm there. Do not execute the proposed setup just to write the guide.

### Plan and write testing guides

In the structured preview, inspect existing project and module test guides, build
commands, CI entry points and tests relevant to the assigned behavior. Include useful
testing pages in the preview even when the prompt names only a controller or feature.
Keep adequate guides unchanged and link to their existing paths. A guide is
adequate when it already tells readers how to verify the assigned behavior at the
available test level. Fill material gaps in place when authorized. Create a guide
when useful content lacks a suitable home and the grant covers it.

- **Project guide.** Briefly state shared commands or link to existing instructions,
  with shared prerequisites and one link per documented module. Keep module commands,
  test inventory and feature gaps in the module guide. Add shared
  or cross-module selection guidance when it affects which checks to run. A gap
  belongs here if it affects project-wide verification, such as an absent shared
  integration suite. Point the docs map at the project guide. In AGENTS, route a
  known module directly to its guide and cross-module work to the project guide.
- **Module guide.** Use one scope line, then map changes to commands, observable
  assertions and nearby test links. Put check-specific prerequisites beside the
  command. Group tests that share a command when useful. When no existing check
  exercises the assigned behavior, use only the scope line and Known gaps. Each
  gap is a short entry with the missing coverage and its evidence. Detailed test
  design belongs in a test plan when requested. A feature page can link here.
  Preserve unrelated entries and their evidence.
- **Gap evidence.** Identify the behavior and test scope searched, cite relevant
  test paths or configuration, and record the reviewed commit. Share the commit
  line across entries from the same snapshot. Link to a shared evidence record
  when needed. For relevant local changes, record
  HEAD and the local paths reviewed. Where no commit exists, cite the reviewed
  paths and working-tree state. Tie refreshed findings to their own snapshot.
  Label unreviewed coverage as such when needed and distinguish it from a search
  that found no relevant check.

Keep per-run metadata, commands actually executed, results and skipped checks
with reasons in the task handoff. Running checks or adding tests requires
appropriate scope and prerequisites.

## Module templates

Use templates under `docs/modules/_module/` (rename `_module`, `_feature`, `_verb`):

| Template | Responsibility |
|---|---|
| [OVERVIEW](../assets/templates/docs/modules/_module/OVERVIEW.md) | Responsibilities, features, components, relationships |
| [API](../assets/templates/docs/modules/_module/api.md) | Controller/API map with DTO/code references, not exhaustive payload specs |
| [Data model](../assets/templates/docs/modules/_module/data-model.md) | Main data, relationships, structural notes |
| [Testing](../assets/templates/docs/modules/_module/TESTING.md) | Check selection, observable scope and snapshot-backed coverage gaps |
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
actual project paths. Resolve relative test links from the module guide's own directory.
Record checks/limits truthfully; TESTING describes how to verify,
not a fabricated passing run. Recheck source bytes for concurrent edits before handoff.
