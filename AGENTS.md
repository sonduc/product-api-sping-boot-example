<!-- spring-kit:begin -->
<!-- Integration template: review the existing instructions, applicable overrides,
and project conventions before proposing where this fragment belongs. Installing
this fragment requires an approved plan. Replace or omit the optional navigation
entries below using source links; leave unknown facts explicit. Do not overwrite
an existing AGENTS.md or treat this template as task authorization. -->

## Working agreement

### Scope and project map

<!-- Optional: project purpose, relevant modules/entry points, applicable local
instructions, and the approved profile or existing project-map path. Link to
sources rather than duplicating them; omit entries that have not been assessed. -->

### Stack, conventions, and checks

- Follow the project's actual stack, architecture, conventions, and verification requirements. Do not introduce framework upgrades, libraries, or architecture changes without authorization.
- Verify against the approved behavior and acceptance criteria using relevant project checks. Report commands actually run, results, and gaps; a command found in configuration is not an executed check.

<!-- Optional: link build/wrapper/CI, representative conventions and required
check commands with prerequisites. Do not invent stack versions or commands. -->

### Context

- Select context for the current question or issue. Expand only for relevant dependencies or missing evidence. Distinguish verified facts, inferences, and unknowns; keep findings traceable to sources.
- Use `spring-context`, when available, for Java/Spring onboarding, explanations, and issue context. Return findings in chat unless saving them is requested. A saved profile is optional and must be checked against current evidence.

### Workflow and authorization

- Before editing, present a plan with scope, intended changes, affected files, and verification. Keep a clear small fix short; include tasks and dependencies for larger work. Resolve consequential business or architecture choices before dependent edits.
- Identify authorization from the current request and still-applicable explicit approvals, including scope, exclusions, and execution limits. If approval is missing, wait for it. If the user already authorized the plan or completion of the task, present the plan and proceed within that grant; do not ask again for each file, step, skill, finding correction, or check it covers.
- One plan approval covers its changes, relevant checks/review, and corrections within that scope. A task-completion grant may cover necessary related files and checks when they serve the authorized outcome and respect explicit limits; it is not blanket permission. Announce a material plan adjustment within that grant without asking for the same permission again.
- Stop only the affected work when new behavior, files, side effects, or unresolved choices exceed the grant. Explain the gap and propose the additional scope, changes, checks, and permission needed; wait for that part while continuing independent authorized work. An investigation, review-only request, old profile, or past task does not by itself authorize fixes.
- Survey prerequisites before dependent execution and group missing setup/permissions in one request. Task authorization and sandbox/environment approval are separate; neither bypasses the other. A blocked check does not authorize installing tools, changing global configuration, weakening tests, or using external services.
- On continuation, reconcile the request, recorded scope/approvals, and current Git/source state. Retain applicable authorization without treating historical grants as new permission. Commit, push, publish, deployment, or installation into another project requires explicit authorization.

### Documentation

- Create, check, or update project documentation only when a person requests it. Coding does not automatically authorize changes to docs, project profiles, or agent instructions. Report documentation impact for the user to decide.
- For documentation work, establish the configured main/master commit and requested scope; distinguish it from a release if different. Report business/code disagreements. Use EN by default and additional languages only within the authorized scope.

### Handoff and reporting

- End with a concise account of the task result and commit state, actual verification and gaps, what can be tried and the expected result, remaining overall work, and the user's next step. Save reports or handoff files only when requested; record authorization with its source and limits rather than as a standing grant.
<!-- spring-kit:end -->
