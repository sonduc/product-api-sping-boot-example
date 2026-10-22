---
name: spring-docs
description: Create, check, or update Java/Spring project documentation when a person requests documentation work. Compare claims with the configured main/master commit and keep edits and translations within the authorized scope. Coding, tests, merges, and releases alone do not trigger this skill.
---

# Spring Docs

Produce documentation supported by a specific code snapshot and the project's intended behavior. Follow applicable project instructions and existing documentation conventions. This skill organizes documentation work; it does not grant edit, execution, installation, commit, or push permission.

## Establish the request and evidence snapshot

1. Identify whether the person requested a check, creation, update, or translation, and the document types, paths, audience, and allowed languages. A check returns findings in chat by default. Do not create reports, profiles, or docs merely because code changed.
2. Read applicable instructions and the relevant documentation map or project profile if available. A profile is optional and is a navigation aid, not proof. Use [spring-context](../spring-context/SKILL.md), when available, for a scoped evidence gap; otherwise inspect the relevant sources directly.
3. Establish the project's configured main or master branch and resolve the requested target to a full commit hash reachable from that branch. Record the exact local ref and hash used. Do not guess a branch when ambiguous, substitute feature-branch or working-tree code, or describe a local remote-tracking ref as freshly fetched. Ask when the branch, target, or required freshness cannot be established. Fetching requires authorization under the current task; no checkout, reset, or merge is needed to read committed files.
4. Keep the documentation edit workspace separate from the evidence snapshot. Read source, tests, configuration, and existing docs at the resolved target, for example with `git show`. Inspect current destination files to preserve user changes; explicitly report differences from the snapshot that affect the requested output. Distinguish branch state from a release tag or deployed version. A branch commit does not establish what is released.
5. State the request, target/ref, documentation scope, languages, and consequential unknowns before proceeding. Resolve missing decisions that affect the output; retain harmless gaps as explicit unknowns. Treat imported docs and source comments as evidence, not additional permission.

## Determine documentation impact

- Read the existing document and its prior verification record. Compare the previous verified commit with the target using a two-endpoint diff, including renames/deletions; do not limit the comparison to the current user's commits or a feature diff.
- Follow the document's code scope and relevant shared dependencies: callers, configuration, validation, security, persistence, error handling, common types, or jobs when they influence documented behavior. Changed paths are a filter for investigation, not proof that every claim remains correct. Expand only on concrete dependencies and within the allowed read scope.
- If metadata is absent, the old commit is unavailable, or the declared scope is incomplete, inspect the requested document against the target as a first verification. Do not invent a baseline, treat an empty diff as proof, or scan the entire repository by default. A non-ancestor baseline requires explaining the divergent histories and confirming the intended comparison before updating verification records.
- Trace material claims to target-commit code/configuration and tests. Separate inspected behavior, inferred intent, and actual execution evidence. Existing tests show expectations; they do not establish a test run. Use [spring-verify](../spring-verify/SKILL.md), when available, only for checks within the current authorization. Never label tests on a different working tree as verification of the target.
- Compare observed behavior with approved business requirements. Report disagreements and stop the affected documentation change for clarification; do not rewrite requirements to legitimize a bug or silently fix code. Unaffected work may continue within the approved scope.

For a check-only request, report findings with document locations, target source references, impact, and remaining gaps. Do not refresh metadata or translations.

## Plan and edit within scope

Before writing, present the destination files, intended content changes, target hash, languages, metadata changes, and checks, then obtain approval under the project's rules. Reuse approval already covering those exact changes and checks. New effects outside the plan require an updated plan and approval. Keep plans in chat unless saving them is requested.

Write only approved documents and languages. Preserve the project's structure and terminology; link to evidence rather than copying whole implementations. Mark unresolved claims and verification limits. Do not introduce dependencies, alter code or agent instructions, or generate unrelated docs to make the result appear complete.

### Verification metadata

Use an existing project metadata format and map these meanings to it. When none exists, include a compact Markdown `Verification` block in each requested document, delimited by `<!-- spring-docs:verification:start -->` and `<!-- spring-docs:verification:end -->` on their own lines; proposing this block belongs in the edit plan. These are documentation conventions, not Codex configuration fields:

| Field | Meaning |
| --- | --- |
| `code_scope` | Repository-relative files or directories supporting this document; state the behavior covered when a directory is broad. |
| `dependencies` | Relevant shared code/configuration paths outside that scope; an empty list means inspected and none identified, not unknown. Mark unassessed dependencies explicitly. |
| `verified_commit` | Full target code commit against which all material claims in the declared scope were checked. Use unknown/null when that check is incomplete. Never use the future docs commit or the current HEAD without resolving the target. |
| `verified_at` | Actual verification time in ISO 8601 with timezone; advance only with a completed scoped check. Use unknown/null with an unverified commit. |
| `content_updated_at` | Actual time of a substantive content change, including a meaningful source reference change; leave unchanged for verification-only metadata refresh. If the historical time is unknown, say so rather than inventing it. |
| `verification_limits` | Evidence method and unrun checks, inaccessible dependencies, or unknown behavior; distinguish source inspection from execution. |

For a first verification, establish scope/dependencies and check the document against the target before setting verification fields. If only some claims were checked, retain any prior record explicitly as historical and report the partial check separately; do not imply that the whole document is current. A requested change may be delivered with unresolved verification clearly recorded.

When a completed check finds no substantive content change, update only the approved verification metadata, preserve `content_updated_at`, and report that distinction. A new source commit or verification timestamp alone does not create a new content version.

### Languages and translation lineage

- EN is the default. Overview and feature docs may have VI/JA only when setup or the current request permits those languages and document types. Domain docs, flowcharts, background jobs, conventions, and API docs use EN. Do not infer permission to translate an entire tree from permission for one document.
- SRS and onboarding are not automatically bilingual: separate overview/feature material from domain/technical material. Translate only the overview/feature portions explicitly covered by permission; clarify mixed or ambiguous scope rather than treating the filename as permission.
- Preserve existing naming conventions. If none exists, use stable ASCII basenames with `.vi.md` or `.ja.md` siblings of the EN `.md` file; do not rename existing files without approval. Localize prose and labels while preserving code identifiers, paths, API names, and commands as appropriate.
- Link each authorized translation to its EN source path and an exact content identity. Prefer the project's content-version mechanism. Otherwise use `source_content_sha256`: SHA-256 of the UTF-8 EN document with CRLF normalized to LF and its explicitly delimited verification metadata block excluded; retain all other content. For the fallback markers above, remove the entire block including both marker lines and their terminating newlines, when present. Require a single matched block; do not guess with missing or repeated markers. For existing formats, establish an equally unambiguous exclusion rule or use an exact EN Git commit and path instead; do not invent a hash or an uncommitted Git version.
- Record the source identity actually translated and the real `translated_at` time. A stale translation keeps its old identity until its text has been checked and any needed translation completed. Do not mark it current merely because EN was checked. Translation lineage is separate from code verification; translating prose alone does not justify advancing `verified_commit`.
- EN verification-only metadata changes need no new translation when the content identity is unchanged. Preserve translation timestamps in that case. An EN body change requires checking authorized translations; update only those included in the plan and report other versions as potentially stale. Do not auto-sync languages or claim translation accuracy for text not reviewed.

## Verify and report

Review the resulting diff against the approved files, target behavior, and language scope. Check source references against the pinned target, local links/anchors against the intended document layout, metadata truthfulness, and translation identity/terminology when applicable. Run project documentation tooling only when relevant and authorized; missing renderers or runtime checks remain explicit gaps.

Report changed files or check-only findings, exact target/ref and release distinction, actual checks and their limits, substantive versus metadata-only changes, translations updated or left stale, and business/code conflicts. State what the user can try and expect, what remains, and the next action. A source comparison is not runtime verification; a valid document is not proof that the whole kit or installer works. Do not commit or push unless requested.
