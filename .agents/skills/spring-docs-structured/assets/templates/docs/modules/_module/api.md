# API — {Module}

{Coverage: assigned controllers/endpoints; not necessarily the whole module.}

## 1. {Controller}
{Purpose and base path. Repeat this group per controller.}

| Shared behavior | Description | Code/config evidence |
|---|---|---|
| Authentication / middleware | {actually applicable chain/filter/interceptor} | {...} |
| Authorization | {common permission if present} | {...} |
| Error handling | {handler and applicable scope} | {...} |
| Common response | {actual wrapper if any} | {...} |

### APIs
| API | Purpose | Request | Response | Endpoint-specific permission | Code evidence |
|---|---|---|---|---|---|
| {method + path} | {...} | {DTO/path/query} | {actual DTO/wrapper} | {...} | {controller method} |

Business flow: {feature link if present}.
{Link DTOs/methods to code; no exhaustive field/JSON/error matrix. Omit irrelevant rows.
Verify security matchers/registration/config, not just the existence of a filter.}
