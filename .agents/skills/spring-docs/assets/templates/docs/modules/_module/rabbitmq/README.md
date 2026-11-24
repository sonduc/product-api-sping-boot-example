# RabbitMQ — {Module}

## 1. Summary
{State inspected scope and limits of counts.}
| Sent message | Queue / consumer counts | Details |
|---|---|---|
| {...} | {...} | {section link} |

| Received message | Publishing module / source document | Queue | Local consumer |
|---|---|---|---|
| {...} | {origin link} | {...} | {consumer link} |

## 2. {Sent message}
### 2.1 Description
{Purpose, feature/job link and evidence.}
### 2.2 Trigger conditions
{Publication sources, conditions/timing and publisher code.}
### 2.3 Message data
{Format and field count.}
| Field | Type | Meaning | Value source | Evidence |
|---|---|---|---|---|
| {...} | {...} | {...} | {...} | {...} |
### 2.4 Exchange, routing and queues
{Exchange/type, sending routing key, receiving queues and bindings with evidence.
Routing key and binding key may differ; not every exchange uses a binding key.
Include queue type/durability/TTL only when relevant and evidenced.}
### 2.5 Distribution and consumers
{Brief description → chart: source → producer → exchange → queue → consumer.}
| Module | Consumer / purpose | Document | Registration evidence |
|---|---|---|---|
| {...} | {...} | {link} | {...} |
{Chart/table placement does not establish execution order.}
### 2.6 Failures
{Publish/unroutable/error paths with evidence and limits. If present, trace source queue
→ dead-letter exchange → routing/binding → error queue; chart when useful.
Link consumers for processing ack/retry. Do not assume delivery guarantees.}

{Repeat group 2 per sent message. Describe common configuration once and link it.}
