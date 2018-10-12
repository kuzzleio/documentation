---
layout: full.html.hbs
algolia: true
title: getMyCredentials
---

# getMyCredentials

{{{since "1.0.0"}}}

Returns credential information for the currently logged in user.

The data returned will depend on the specified authentication strategy, and they should not include any sensitive information.

The result can be an empty object.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/credentials/<strategy>/_me
Method: GET  
Headers: Authorization: "Bearer <authentication token>"
```

### Other protocols

```js
{
  "controller": "auth",
  "action": "getMyCredentials",
  "strategy": "<strategy>",
  "jwt": "<authentication token>"
}
```

---

## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy to retrieve

---

## Response

The result content depends on the authentication strategy. 

Example with the `local` authentication strategy:

```javascript
{
  "status": 200,
  "error": null,
  "action": "getMyCredentials",
  "controller": "auth",
  "result": {
    "username": "MyUser",
    "kuid": "<kuid>"
  }
}
```
