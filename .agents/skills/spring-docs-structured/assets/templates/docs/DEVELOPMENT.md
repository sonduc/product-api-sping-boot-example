# Local development

{Starting directory and what the reader will have running at the end.}

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
| {...} | {project command or inferred command with source and inline “not run this way” note} | {...} |

{Identify automatic startup steps. Omit steps the project does not need.}

## 4. Run and stop
{Working directory, run command and profile. For an inferred step, cite the manifest,
script or configuration for the component's language/runtime and say beside the step
that this way of running has not been verified. Identify required values
or prerequisites the reader still needs to confirm at the affected step.}
- Access: {URL/port}.
- Startup check: {health/log/observable behavior}.
- Stop application/local dependencies: {instructions}.

## 5. Known environment problems
| Symptom | Diagnosis | Resolution |
|---|---|---|
| {known issue} | {...} | {...} |

Checks: {link to existing test instructions, if applicable}.
