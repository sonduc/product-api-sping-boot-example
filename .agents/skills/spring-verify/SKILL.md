---
name: spring-verify
description: Verify Java/Spring changes against acceptance criteria using the project's build, unit and integration tests, and other relevant checks. Use for a verification request or an approved coding plan; discovering a command or inspecting code alone does not establish a passing check.
---

# Spring Verify

Produce execution evidence for the requested behavior and report its limits. Follow project instructions and the authorized verification scope. Verification alone grants no code, documentation, dependency, or environment changes; retain any applicable edit/check authority already granted for the current task.

## Select checks from evidence

1. Establish the change or commit under verification, relevant working-tree differences, and acceptance criteria. Use the current coding plan when this is part of implementation; for a standalone request, clarify consequential missing criteria or scope.
2. Inspect relevant project instructions, build files, wrappers, test configuration, CI, and existing tests. A saved profile is a navigation aid, not proof of current configuration. Use [spring-context](../spring-context/SKILL.md), when available, only for context gaps relevant to the checks.
3. Map each acceptance criterion to evidence: unit tests for isolated rules; integration tests for affected application, persistence, or external boundaries; build/static checks and runtime probes where relevant. Follow the project's actual conventions. Explain checks omitted or unavailable rather than imposing a framework, Docker, Testcontainers, or coverage target.
4. Identify exact commands, modules/profiles, and prerequisites from project evidence. Distinguish declared versions from the installed runtime. If no supported command is found, report the missing evidence instead of inventing a default command. Check relevant official documentation before relying on version-specific behavior.

Use execution already authorized by the user's request, approved coding plan, or explicit task-completion grant without asking again on a skill/step change. Identify prerequisites before execution and group missing setup/permissions in one request. Task approval and sandbox/environment approval are separate. If a needed check goes beyond the grant, present the command, prerequisites, expected side effects, and missing permission before running it. Continue independent covered checks. Do not run mutation, deployment, destructive data setup, or external-service actions merely because a discovered script includes them.

## Execute and interpret

- Run relevant checks in an order that gives useful feedback, usually focused checks before broader required checks. Record commands, working directory, meaningful options, exit status, and results without exposing secrets.
- Distinguish a test assertion failure from compilation failure, unavailable dependency/service, unsupported runtime, permission failure, or interrupted execution. Report which behavior was actually exercised. A successful build does not establish integration or HTTP behavior.
- Inspect test discovery and summaries. Zero executed tests, skipped integration tests, or a green command that excluded the changed behavior are gaps, not a passing acceptance criterion. Do not treat stale reports as current execution evidence.
- Stop affected checks when prerequisites or permissions are missing. Do not install dependencies beyond the authorized setup, alter environment configuration, disable tests, or weaken checks to obtain a green result. Report the concrete blocker and the next step that would resolve it.
- Correct failures through the current coding workflow when the approved plan or explicit task grant covers them; do not ask again for those fixes. A verification-only request does not authorize fixes. After an authorized fix, rerun affected checks and any required broader checks; repeat only when changes, failures, or unresolved concerns justify it.

## Report acceptance evidence

For each criterion, state **passed**, **failed**, or **not verified**, with the supporting check and its limits. Keep command results separate from acceptance conclusions: an environment failure leaves the affected behavior unverified; an observed assertion mismatch is a failure of the exercised expectation.

Summarize actual commands/results, tests executed or skipped, remaining blockers, and relevant behavior not covered. If existing tests are insufficient, identify the needed test/manual check and add it only when edit authorization covers it, reusing current authority. Do not claim that review, a source inspection, or a discovered CI command was an executed test.

Finish with what can be tried, prerequisites and expected result, remaining verification work, and the user's next action. Return the report in chat unless saving it is explicitly requested and approved.
