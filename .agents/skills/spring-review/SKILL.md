---
name: spring-review
description: Review Java/Spring diffs or commits for behavioral defects, regressions, and conflicts with project conventions or approved acceptance criteria. Use for a review request or the review step in an approved coding plan; review alone does not authorize fixes.
---

# Spring Review

Review the requested change against intended behavior and project evidence. Follow applicable instructions and keep review separate from edit or execution permissions. This workflow runs in the current agent; it does not require a separate agent.

## Establish the review basis

1. Identify the requested diff, commit range, or working-tree scope and relevant acceptance criteria. Inspect Git state and distinguish committed, staged, and unstaged changes when relevant. Do not silently choose a different base or attribute unrelated user changes to the task. Clarify a missing base when it changes what is reviewed.
2. Read applicable instructions, the diff, and enough surrounding code, callers, configuration, and tests to trace changed behavior. Use [spring-context](../spring-context/SKILL.md), when available, for a specific context gap. Expand only where a dependency or missing fact affects the review.
3. Compare code with the approved business rules and existing conventions. When intended behavior is unclear or docs conflict with code, describe the uncertainty and its consequence; do not invent requirements or treat a possible design preference as a confirmed defect.

## Review behavior and risk

Prioritize concrete defects and regressions over stylistic preferences. Trace relevant success, failure, and boundary cases through the affected flow. Inspect the boundaries that the change actually touches, such as API/error contracts, validation, authorization, transaction and persistence behavior, concurrency, caching, or asynchronous effects. Do not turn this into a mandatory whole-project audit.

Check whether tests exercise the intended behavior and regressions rather than only the implementation shape. Distinguish missing coverage from a demonstrated runtime defect. Use existing verification evidence with its commit and scope; it does not prove behavior beyond the checks that ran.

If execution is needed, use [spring-verify](../spring-verify/SKILL.md), when available, within the current request or approved plan. Otherwise identify project checks directly. Review permission alone does not authorize running arbitrary setup or services; report unrun checks and prerequisites when execution is outside scope.

## Report findings with evidence

Order actionable findings by impact. For each finding, include:

- A concise description and severity justified by the consequence.
- The changed file and a narrow line location, plus relevant supporting sources when needed.
- The triggering input or condition, expected versus observed/inferred behavior, and why the change causes the problem.
- Evidence such as the code path or an actual failing check; label inference and missing runtime verification explicitly.

Do not require a minimum number of findings or questions. If no actionable findings are supported, say so and state remaining coverage or verification limits. Keep open requirements and optional suggestions distinct from defects. A clean review is not proof that tests ran or that all acceptance criteria passed.

## Handle corrections within scope

A review-only request returns findings in chat; it does not itself authorize code fixes, saved reports, documentation, or instruction changes. Retain any still-applicable edit/check authority from the current request or approved plan; switching to review does not revoke it. When an approved coding plan or explicit task-completion grant covers a finding, correct it through that workflow, rerun affected checks, and re-review without repeat approval. Stop only affected work outside that grant, propose the additional changes/checks and missing permission, and wait for that part. A profile or historical review record does not supply new authority.

Finish with findings or their absence, actual review/verification coverage and gaps, the next useful check and expected result, remaining work, and the user's next step. Do not edit docs to reconcile a business/code disagreement or commit/push merely because review is complete.
