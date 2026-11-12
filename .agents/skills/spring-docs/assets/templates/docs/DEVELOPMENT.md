# Local development

## 1. Environment requirements
| Component | Version / requirement | How to check | Needed for |
|---|---|---|---|
| {tool/runtime/service} | {...} | {command} | {build/run/check} |

## 2. Local configuration
| Configuration | Purpose | Source / how supplied |
|---|---|---|
| {profile/env/file} | {...} | {sample or instructions} |

- {How to create local configuration from existing samples.}
- {How credentials are supplied; never include secret values.}
- {Local-only files that must not be committed.}

## 3. Initialization
1. {Prepare configuration.}
2. {Start dependencies using existing project tooling.}
3. {Initialize schema/migrations/sample data when required.}

| Step | Command | Expected completion signal |
|---|---|---|
| {...} | {verified project command} | {...} |

{Identify automatic startup steps. Omit steps the project does not need.}

## 4. Run and stop
{Actual run command and profile.}
- Access: {URL/port}.
- Startup check: {health/log/observable behavior}.
- Stop application/local dependencies: {instructions}.

## 5. Known environment problems
| Symptom | Diagnosis | Resolution |
|---|---|---|
| {known issue} | {...} | {...} |

Checks: {testing guide link}. Keep setup in one place; link existing README setup instead
of duplicating it when appropriate.
