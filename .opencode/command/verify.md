---
description: Run mvn clean verify and enforce JaCoCo coverage gates (line >= 70%, branch >= 60%).
agent: test-engineer
---

Run full verification inside the dev container:

```
docker compose exec product-api-dev mvn clean verify
```

Then check the JaCoCo report and confirm:
- line coverage >= 70%
- branch coverage >= 60%

Report pass/fail with the actual coverage numbers. If below gate, list the uncovered packages and suggest which tests to add.
