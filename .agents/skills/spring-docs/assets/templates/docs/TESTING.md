# Testing and quality checks

## 1. Existing checks
| Type | What it checks | Location / tool | Prerequisites |
|---|---|---|---|
| {unit/integration/API/...} | {...} | {...} | {...} |
| {format/lint/static/...} | {...} | {...} | {...} |

{Only list checks actually present.}

## 2. Commands
| Goal | Command | Scope |
|---|---|---|
| One test | {command} | {...} |
| Related tests | {command} | {...} |
| Full check suite | {command} | {...} |
| Code quality | {command} | {...} |

{Test configuration, fixtures, dependent services and report paths.}

## 3. Select checks by impact
| Change | Checks to consider |
|---|---|
| Business logic | {rules/branches/boundaries} |
| API/security | {validation, contract, permission and data scope} |
| Database | {queries, mapping, constraints, migrations/transactions} |
| Jobs/events/messages | {state, retry, duplicate and partial-failure behavior} |
| Build/shared configuration | {affected checks and regression scope} |

## 4. Test conventions
- {Existing test location, naming and fixture conventions.}
- Check observable behavior and meaningful cases.
- Add a regression test for a bug when feasible.
- Do not weaken assertions or disable checks to legitimize failures.

## 5. Verification evidence
For each change report the source snapshot (commit/local changes as relevant), actual
commands/scope, result and reports/logs where available. State unrun checks, causes and
limits. Distinguish reading source/tests, executing checks and runtime verification.
Old-snapshot results do not certify new changes. This guide documents how to check;
it is not itself evidence that those checks passed.

## 6. Known limits
{Missing coverage, skipped/flaky checks, unsupported environments and unverified behavior.}
