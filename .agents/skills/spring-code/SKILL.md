---
name: spring-code
description: Plan and implement Java/Spring features or bug fixes using project conventions, then coordinate tests and review against approved acceptance criteria. Use for requested code changes; explanation, standalone verification, review, and documentation work have separate scopes.
---

# Spring Code

Turn a requested behavior into a reviewed change within one approved plan. Follow applicable project instructions and retain the project's stack, architecture, conventions, and test practices. This skill organizes work; selecting it grants no additional permissions.

## Establish the change

1. Identify the desired behavior, acceptance criteria, and affected area. For a bug, separate the observed behavior from the intended behavior and distinguish inspected evidence from a reproduced failure.
2. Inspect the relevant instructions, current Git state, nearby code, tests, and build/CI configuration. Preserve existing user changes. Use [spring-context](../spring-context/SKILL.md), when available, for scoped investigation; otherwise gather the same evidence directly. Do not require full onboarding or a saved profile.
3. Resolve business or architecture choices that affect implementation before proposing edits. Investigate only within the assigned scope, explain the consequential options, and wait for the user's decision. Missing facts that do not affect the change can remain explicit unknowns.

## Plan and obtain approval

Present the scope, intended behavior, affected files, and verification before every edit. Keep a small, clear fix to a short plan. For a larger, defined feature, split the plan into tasks with dependencies and acceptance criteria. Keep plans/tasks in chat unless saving them is requested and included in the approved file scope.

Include the relevant unit tests, integration tests, and review, with commands and prerequisites supported by the project. Explain any unavailable or unnecessary check. Do not assume Maven, Gradle, Docker, a coverage threshold, or a new testing library. Use [spring-verify](../spring-verify/SKILL.md), when available, to prepare checks, and [spring-review](../spring-review/SKILL.md) for review. These are workflows for the current agent, not requests to spawn agents.

Apply the project's approval rules to these four cases:

| Situation | Required action |
| --- | --- |
| Small change with clear requirements | Present a short file/change/check plan and wait for approval before editing. |
| Large feature with clear requirements | Present tasks, dependencies, files, and checks; wait for approval before implementing. |
| Open business or architecture choice | Present evidence and options; wait for the decision, then obtain approval for the edit plan. |
| New effects outside the approved plan | Stop the affected work, explain the impact, and obtain approval for an updated plan before expanding. |

One approval covers the changes and checks listed in that plan. Reuse approval already given for the same scope; do not ask again for a listed test or review. A request to investigate or plan is not approval to edit.

When new effects exceed the approved plan, stopping with a blocker is not a complete handoff. Explain why the current scope cannot meet the requirement, then present an updated plan identifying the additional files/scope, intended changes, and verification, with any execution limits. Explicitly identify the new approval needed and wait before editing the affected area. If a consequential business or architecture choice prevents a concrete plan, state the options and the decision needed first; do not silently choose it or present an unresolved choice as an approved change.

## Implement and close the loop

- Make the approved changes using existing patterns and actual Java/Spring versions. Do not introduce unrelated refactors, dependency upgrades, libraries, or architecture changes.
- Add or update tests that demonstrate the approved behavior and relevant regressions. Choose unit and integration coverage by the changed boundaries; tests should assert outcomes, not merely mirror the implementation. A check passing is evidence only for the behavior it exercises.
- Run the approved verification and review the resulting diff. If the companion skills are unavailable, perform those steps directly using the project's checks and evidence-based review. Do not skip them silently.
- Correct failures and findings that fit the approved files, behavior, and checks, then rerun affected checks. Obtain an updated plan for effects outside that scope. Missing infrastructure or permissions are verification gaps, not permission to change setup or weaken assertions.
- Coding does not trigger documentation work. Report documentation impact for the user to decide; editing docs, project profiles, or agent instructions requires an explicit request and an approved plan. Commit/push also require explicit authorization.

Finish with the behavior changed, affected files, acceptance results, actual checks/review and remaining findings or gaps. State what can be tried and its expected outcome, remaining work, and the user's next step. Do not claim completion of an unmet or unverified acceptance criterion.
