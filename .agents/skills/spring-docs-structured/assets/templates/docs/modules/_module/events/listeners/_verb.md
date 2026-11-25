# {Name} — Event listener

## 1. Description
{Purpose, feature link and code evidence. Do not invent business rationale.}

## 2. Trigger conditions
{Source event/document link, used data/conditions, sync/async, transaction timing and registration. Include code/config evidence.}

## 3. Processing flow
{Brief description → chart of actual steps/branches/outcomes → step table below.
Listener starts at event receipt; consumer starts at message receipt, through actual
ack/error behavior. Do not repeat publisher topology.}

| Step | Processing / impact | Code/config evidence |
|---|---|---|
| {step} | {state/data/transaction/external effects} | {source} |

## 4. Failures
| Failure | Handling / impact | Code/config evidence |
|---|---|---|
| {case} | {actual retry/rollback/idempotency/recovery if any} | {source} |

{Include relevant duplicate/concurrent processing and partial failures.
For consumers cover actual ack/reject/requeue and link the map for error routing.
State important unknowns and verification limits; do not invent mechanisms.}
