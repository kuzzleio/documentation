---
layout: full.html.hbs
algolia: true
title: logout
---


# logout

{{{since "1.0.0"}}}

Revokes the provided authentication token.

If there were any, real-time subscriptions are cancelled.


## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)

---

## Response

```js
{
  "status": 200,
  "error": null,
  "controller": "auth",
  "action": "logout",
  "requestId": "<unique request identifier>",
  "result": {}
}
```
