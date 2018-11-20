---
layout: full.html.hbs
algolia: true
title: credentialsExist
---


# credentialsExist

{{{since "1.0.0"}}}

Checks that the current authenticated user has credentials for the specified authentication strategy.


## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy to be tested

---

## Response

Result is a boolean telling whether credentials exist for the provided authentication strategy:

```js
{
  "status": 200,
  "error": null,
  "action": "credentialsExist",
  "controller": "auth",
  "result": true
}
```
