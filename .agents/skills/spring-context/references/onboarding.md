# Onboarding a Java/Spring project

Read this guide for onboarding or a broad explanation. A focused issue investigation can use the main skill directly.

Adapt the depth to the requested area and the evidence available. An overview request does not require inspecting every domain or entity. A project without agent instructions, docs, or a build file can still receive a useful partial explanation; identify what cannot yet be established.

## Overview

Use the root/module structure, relevant build files, existing docs, and application entry points to establish the project's purpose, module boundaries, and stack. Separate a documented purpose from behavior confirmed in code. Reuse existing maps as starting points and inspect the evidence needed for the current question.

For an empty or early project, report what exists and what is undecided. For a legacy project, follow its actual conventions and versions. Do not turn onboarding into scaffolding, an upgrade, or an architecture redesign.

## Domain relationships

Identify the principal business areas in scope and how they collaborate. Ground names and relationships in code, APIs, messages, jobs, tests, or existing business docs. Explain ownership or direction of calls when the sources support it. Mark an inferred boundary explicitly; package names alone are insufficient evidence.

Begin with the relationships that help the user understand the requested area. Defer exhaustive class and entity lists.

## Main flows

Trace a representative flow relevant to the request: HTTP endpoint, message handler, scheduled job, batch entry point, or other actual trigger → business logic → state or external effects → result. Note consequential validation, transaction boundaries, asynchronous steps, and error paths only where evidence supports them.

Describe current behavior and link material steps to source locations. Tests show intended assertions; unexecuted tests are not proof that the flow passes. Use a small Mermaid flowchart in chat when it clarifies the relationships. Name unresolved steps instead of inventing them.

## Domain detail and entities

After the overview, relationships, and flow, inspect the domain details needed for the question. Read entities or persistence mappings when they explain ownership, invariants, relationships, queries, or transaction behavior. Do not infer a database constraint from a Java field or an unverified migration assumption.

If business docs and implementation disagree, report the disagreement and evidence. Do not rewrite the intended behavior to make the code appear correct.

## Evidence and continuation

Conclude at the requested depth with verified facts, useful inferences, and consequential unknowns. Offer a specific next area to inspect if needed. Missing dependencies, inherited configuration, external systems, or absent CI should limit only the claims they affect.

Onboarding output remains in chat unless writing has been requested and planned. If a saved profile or document is requested, preserve existing project documentation, use the allowed language, and follow the approved file scope. Do not start documentation synchronization or translation as an onboarding side effect.
