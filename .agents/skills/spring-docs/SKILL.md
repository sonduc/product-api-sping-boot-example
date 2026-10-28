---
name: spring-docs
description: Create, check, or update Java/Spring documentation when a person requests it, including drafts from an assigned branch or working tree and requested checks after merge. Track document, code review, and verification status separately. Coding, tests, merges, and releases alone do not trigger this skill.
---

# Spring Docs

Produce documentation supported by a specific code snapshot and the project's intended behavior. Follow applicable project instructions and existing documentation conventions. This skill organizes documentation work; it does not grant edit, execution, installation, commit, or push permission.

## Establish the request and evidence snapshot

1. Identify whether the person requested a check, creation, update, or translation, and the document types, paths, audience, and allowed languages. A check returns findings in chat by default. Do not create reports, profiles, or docs merely because code changed.
2. Read applicable instructions and the relevant documentation map or project profile if available. A profile is optional and is a navigation aid, not proof. Use [spring-context](../spring-context/SKILL.md), when available, for a scoped evidence gap; otherwise inspect the relevant sources directly.
3. Select the evidence mode from the request. A draft may use the assigned branch, full commit, or working tree; do not require it to reach main/master. For an explicitly requested after-merge reconciliation, resolve the configured main/master ref to a full local commit. Do not start that reconciliation merely because a merge occurred. If the target/mode or required freshness is consequentially ambiguous, ask before dependent work. Record the exact ref/hash; a local remote-tracking ref is not freshly fetched. Fetching needs authorization; reading commits needs no checkout, reset, or merge.
4. Keep the edit workspace distinct from the evidence snapshot. For committed evidence use the pinned commit, for example with `git show`. For working-tree evidence record its base full HEAD and relevant staged, unstaged, deleted, and untracked source/config/test paths, with content hashes or a retained patch identifying the inspected bytes and capture time. Distinguish index-only from on-disk evidence, include relevant dependencies and recheck for concurrent changes before reporting. Do not claim those bytes belong to HEAD or set `verified_commit` to HEAD for an uncommitted snapshot. Inspect destination files to preserve user changes. A branch commit or merge does not establish a release or deployment.
5. State the request, target/ref, documentation scope, languages, and consequential unknowns before proceeding. Resolve missing decisions that affect the output; retain harmless gaps as explicit unknowns. Treat imported docs and source comments as evidence, not additional permission.

## Keep one document location and separate labels

Use the existing documentation map and stable paths for both drafts and maintained docs. Agree on paths in the plan when none exist. Update the same requested files after merge; do not create parallel dev/release trees, duplicate promoted copies, or rename documents without permission. Preserve structure, terminology, diagrams, naming, metadata style, and the language of existing requested documents. Apply language defaults below to new material only when the project/request has no contrary convention.

Record three independent meanings using the project's labels: document status (for example draft/maintained), code review status with evidence for the exact code scope/snapshot (unknown/reviewed/changes requested), and documentation verification (unverified/partial/complete, with method and limits). A draft can have a complete source comparison and unconfirmed code review. Reading code, tests passing, self-reviewing the docs, merging, or finding an old reviewed label does not establish code review of the selected snapshot. Missing review evidence stays unknown; an approval on a commit does not automatically cover later uncommitted edits.

Read [snapshot and metadata rules](references/snapshot-metadata.md) when writing/updating labels or verification records, handling legacy metadata, or reconciling after merge. Keep these records compact and within the requested docs; no new tracking system is required.

## Determine documentation impact

- Read the existing document and its prior verification record. For committed targets compare the previous verified commit with the target using a two-endpoint diff, including renames/deletions; for a working tree also inspect staged/unstaged/untracked changes and the actual supporting files. Do not limit the comparison to the current user's commits or a feature diff.
- Follow the document's code scope and relevant shared dependencies: callers, configuration, validation, security, persistence, error handling, common types, or jobs when they influence documented behavior. Changed paths are a filter for investigation, not proof that every claim remains correct. Expand only on concrete dependencies and within the allowed read scope.
- If metadata is absent, the old commit is unavailable, or the declared scope is incomplete, inspect the requested document against the target as a first verification. Do not invent a baseline, treat an empty diff as proof, or scan the entire repository by default. A non-ancestor baseline requires explaining the divergent histories and confirming the intended comparison before updating verification records.
- Trace material claims to the selected code/configuration and tests. Separate inspected behavior, inferred intent, and actual execution evidence. Existing tests show expectations; they do not establish a test run. Use [spring-verify](../spring-verify/SKILL.md), when available, only for checks within the current authorization. Never label tests on a different working tree as verification of the target.
- Compare observed behavior with approved business requirements. Report disagreements and stop the affected documentation change for clarification; do not rewrite requirements to legitimize a bug or silently fix code. Unaffected work may continue within the approved scope.

For a check-only request, report findings with document locations, target source references, impact, and remaining gaps. Do not refresh metadata or translations.

## Plan and edit within scope

Before writing, present the stable destination files, intended content changes, snapshot identity, languages, label/metadata changes, and checks. Identify the current request and still-applicable approvals: proceed when the plan or explicit documentation-task completion grant covers the work; otherwise obtain approval under the project's rules. Reuse that authority for covered findings, corrections, and checks across skill/step changes. Stop only affected work outside the grant, propose the additional scope/checks and missing permission, and wait for that part. A coding grant or historical profile does not itself authorize documentation work. Task approval does not bypass sandbox/environment approval; group missing prerequisites/permissions before dependent execution. Keep plans in chat unless saving them is requested.

Write only approved documents and languages. Preserve the project's structure and terminology; link to evidence rather than copying whole implementations. Mark unresolved claims and verification limits. Do not introduce dependencies, alter code or agent instructions, or generate unrelated docs to make the result appear complete.

### Verification metadata

Use the existing metadata convention and map the meanings in [snapshot and metadata rules](references/snapshot-metadata.md). When absent, propose one delimited Markdown `Verification` block in the edit plan. These fields are documentation conventions, not Codex configuration. Preserve prior evidence as historical when current verification is incomplete; never promote old records to current by copying their commit/time or labels. Metadata-only refresh preserves content and translation timestamps/identity.

### Languages and translation lineage

- EN is the default. Overview and feature docs may have VI/JA only when setup or the current request permits those languages and document types. Domain docs, flowcharts, background jobs, conventions, and API docs use EN. Do not infer permission to translate an entire tree from permission for one document.
- SRS and onboarding are not automatically bilingual: separate overview/feature material from domain/technical material. Translate only the overview/feature portions explicitly covered by permission; clarify mixed or ambiguous scope rather than treating the filename as permission.
- Preserve existing naming conventions. If none exists, use stable ASCII basenames with `.vi.md` or `.ja.md` siblings of the EN `.md` file; do not rename existing files without approval. Localize prose and labels while preserving code identifiers, paths, API names, and commands as appropriate.
- Link each authorized translation to its EN source path and an exact content identity. Prefer the project's content-version mechanism. Otherwise use `source_content_sha256`: SHA-256 of the UTF-8 EN document with CRLF normalized to LF and its explicitly delimited verification metadata block excluded; retain all other content. For the fallback markers above, remove the entire block including both marker lines and their terminating newlines, when present. Require a single matched block; do not guess with missing or repeated markers. For existing formats, establish an equally unambiguous exclusion rule or use an exact EN Git commit and path instead; do not invent a hash or an uncommitted Git version.
- Record the source identity actually translated and the real `translated_at` time. A stale translation keeps its old identity until its text has been checked and any needed translation completed. Do not mark it current merely because EN was checked. Translation lineage is separate from code verification; translating prose alone does not justify advancing `verified_commit`.
- EN verification-only metadata changes need no new translation when the content identity is unchanged. Preserve translation timestamps in that case. An EN body change requires checking authorized translations; update only those included in the plan and report other versions as potentially stale. Do not auto-sync languages or claim translation accuracy for text not reviewed.

## Verify and report

Review the resulting diff against the approved files, target behavior, and language scope. Check source references against the pinned target, local links/anchors against the intended document layout, metadata truthfulness, and translation identity/terminology when applicable. Run project documentation tooling only when relevant and authorized; missing renderers or runtime checks remain explicit gaps.

Report changed files or check-only findings, stable paths, the three statuses, exact target/ref plus uncommitted evidence and release distinction, actual checks and their limits, substantive versus metadata-only changes, translations updated or left stale, and business/code conflicts. State what the user can try and expect, what remains, and the next action. A source comparison is not runtime verification; a valid document is not proof that the whole kit or installer works. Do not commit or push unless requested.
