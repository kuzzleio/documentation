---
layout: full.html.hbs
algolia: true
title: deleteMyCredentials
---


# deleteMyCredentials

{{{since "1.0.0"}}}

Deletes credentials associated to the current user.

If the credentials that generated the current JWT are removed, the user will remain logged in until they log out or their session expire. After that, they will no longer be able to log in with the deleted credentials.


## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy to delete

---

## Response

Returns a confirmation that the credentials are being deleted:

```js
{
  "status": 200,
  "error": null,
  "action": "deleteMyCredentials",
  "controller": "auth",
  "result": {
    "acknowledged": true
  }
}
```
