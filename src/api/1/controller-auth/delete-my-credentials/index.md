---
layout: full.html.hbs
algolia: true
title: deleteMyCredentials
---

# deleteMyCredentials

{{{since "1.0.0"}}}

Delete the current user's credentials for the specified authentication strategy.

If the credentials that generated the current JWT are removed, the user will remain logged in until he logs out or his session expires, after that they will no longer be able to log in with the deleted credentials.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/credentials/<strategy>/_me
Method: DELETE  
Headers: Authorization: "Bearer <authentication token>"
```

### Other protocols

```js
{
  "controller": "auth",
  "action": "deleteMyCredentials",
  "strategy": "<strategy>",
  "jwt": "<authentication token>"
}
```

---

## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy to delete

---

## Response

Return a confirmation that the credentials are being deleted:

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
