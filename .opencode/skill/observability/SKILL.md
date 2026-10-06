---
name: observability
description: Use when configuring Actuator, Micrometer Tracing, Zipkin, or structured logging. Triggers: "health", "trace", "metric", "log", "zipkin".
---

# Observability

## When to Use

- Actuator / health / metrics
- Distributed tracing / Zipkin
- Structured logging

## Quick Reference

| Concern | Convention |
|---|---|
| Health | `/actuator/health` |
| Metrics | `/actuator/metrics` |
| Tracing | Micrometer Tracing -> Zipkin |
| Logging | JSON structured, correlation ID |

## Setup Notes

- Expose `health`, `info`, `metrics`, `prometheus` (or `httptrace`) via Actuator.
- `management.tracing.sampling.probability=1.0` for local dev.
- Zipkin endpoint from `.env`, never hardcoded.
