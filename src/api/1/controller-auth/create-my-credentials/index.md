---
layout: full.html.hbs
algolia: true
title: createMyCredentials
---


# createMyCredentials

{{{since "1.0.0"}}}

Creates new credentials for the current user.


## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy to use


## Response

The result content depends on the authentication strategy. 

Example with the `local` authentication strategy:

```js
{
  "status": 200,
  "error": null,
  "action": "createMyCredentials",
  "controller": "auth",
  "result": {
    "username": "MyUser",
    "kuid": "<kuid>"
  }
}
```
