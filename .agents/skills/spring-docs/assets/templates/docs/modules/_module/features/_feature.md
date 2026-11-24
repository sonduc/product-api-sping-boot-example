# {Feature}

## 1. Overview
{Purpose, scope, complete list of assigned operations and impacts; definition of done.
Distinguish accepting a request, committing business state and finishing side effects.}

## 2. Entry points, inputs and outputs
| Entry / operation | Input | Output | Code/config evidence |
|---|---|---|---|
| {API/action/caller} | {...} | {...} | {...} |

## 3. Conditions and rules
{Separate field validation, permissions/data scope, business rules and state meanings.
Place evidence beside each rule; do not infer business rationale from code alone.}

## 4. Flow and impacts
{Main/alternative/error paths using a suitable chart or table. Show data changes,
transaction boundaries, calls to other modules and relevant background side effects.
Evidence belongs beside the step; link job/listener/consumer documents when present.}

## 5. Verification and unknowns
{Link to the relevant check or gap in the module testing guide when available.
Otherwise cite relevant test evidence here. Record feature-specific unknowns and
intended/observed conflicts. Distinguish existing checks from checks executed.}
