# Project architecture

## 1. Code structure
{Short actual directory/package tree: locations useful for finding or placing code.}

| Location | Responsibility | Files placed here |
|---|---|---|
| {path/package} | {responsibility} | {file types/current examples} |

Evidence: {build/modules/packages/config}.

## 2. Responsibilities and dependencies
| Component / layer | Responsibility | Allowed dependencies / calls | Boundaries |
|---|---|---|---|
| {actual component} | {...} | {...} | {...} |

{Small dependency diagram when it clarifies the project.}

## 3. Code organization rules
- {How to choose the module/package for new code.}
- {Module boundaries and communication.}
- {Criteria for shared components.}
- {Locations of tests, resources, migrations and generated-code sources.}

## 4. Shared architectural mechanisms
| Mechanism | Project organization | Evidence / document |
|---|---|---|
| {transaction/security/events/config/...} | {brief description} | {source/link} |

{Keep cross-module mechanisms here; business flows belong in module docs.}

## 5. Constraints and unknowns
- {Established constraint and its supported rationale.}
- {Legacy exception and applicable scope.}
- {Missing evidence or consequential decision.}
