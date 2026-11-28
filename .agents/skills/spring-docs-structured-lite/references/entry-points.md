# Finding evidence outside Spring

Start at the requested symbol or path. Use the closest repo instructions, source
registration, manifest, and configuration to identify the module's stack. In a
monorepo, follow cross-module links only when the requested behavior depends on
them. A root manifest alone does not identify the module's runtime.

## JavaScript and TypeScript

Use the module's package.json and applicable workspace or lockfile to confirm
dependencies and commands. Follow the mounted router, HTTP method and path into
middleware, handlers and the service calls that determine the result. For events
or jobs, follow registration, dispatch and subscriber or worker code. Check
project scripts and CI for the actual test commands.

## Python

Use pyproject.toml, dependency files and existing CI to locate tooling. Follow
URL registration, CLI entry points, subscriber registration or task schedules
to the implementation. For events and jobs, trace trigger, routing, handlers
and business effects. Confirm checks from repo configuration or CI.

## Other stacks

Use the project's own manifest, build or run entry points and existing checks.
Identify what actually registers each route, command, listener or job and
follow its relevant dependencies. Treat unresolved links as evidence gaps.

Use the existing project commands within the approved checks. Describe test
expectations and actual executions separately. Document messaging behavior in
the corresponding feature and reuse existing approved documentation paths.
Use RabbitMQ template slots for RabbitMQ evidence, or zero-byte placeholders
when RabbitMQ behavior is absent.
