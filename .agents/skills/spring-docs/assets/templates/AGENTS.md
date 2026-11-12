# Agent working instructions

## 1. Receive the task
- Establish the goal, scope and expected outcome.
- Read applicable instructions; inspect branch, HEAD and local changes before editing.
- Preserve changes outside scope. Ask about gaps affecting decisions.
- On continuation, restore the checkpoint, source state and still-applicable authority.

## 2. Select context
| Need | Source |
|---|---|
| Purpose, stack/version sources | `README.md` and actual build/config |
| Structure, layer responsibilities, dependency direction | `docs/ARCHITECTURE.md` |
| Naming, errors and technical rules | `docs/CODING_RULES.md` |
| Environment, initialization and local run | `docs/DEVELOPMENT.md` or existing README setup |
| Check selection, commands and evidence | `docs/TESTING.md` |
| Business context | `docs/README.md` → relevant module |

Read only relevant sections; adapt these routes to existing project paths.
Verify material behavior in code/config/tests and follow concrete dependencies.
Report documentation/implementation conflicts without deciding intended behavior silently.
Missing docs alone do not authorize creating them.

## 3. Implement and verify
- Before edits, present a proportionate plan: scope, behavior/files, checks, prerequisites.
- Preserve existing structure, conventions, libraries and tooling.
- For bugs, find the cause, reproduce and add a regression test when feasible.
- Select checks by impact using the testing guide; self-review and fix in-scope findings.
- Distinguish source inspection, existing tests and checks actually run.
- Report blocked checks with causes and verification limits.

## 4. Authority and boundaries
- Complete investigation, implementation, tests/checks and review within the current grant;
  do not request the same permission for each covered step.
- Ask for consequential missing decisions or additional scope; continue independent work.
- Do not overwrite or revert local changes outside scope.
- Commit/push, publish/deploy, toolchain installation and global configuration require
  authorization covering those actions.
- Create/update docs, AGENTS and checkpoints only when assigned.
- Environment permissions remain separate; these instructions do not expand the sandbox.

## 5. Completion and handoff
- Report the result, principal changes, actual checks, outcomes and limits.
- State how to try it, expected behavior and remaining work when relevant.
- Do not use old-snapshot evidence to certify new changes.
- Save a checkpoint only when requested, retaining state, decisions, authority and next steps.
