---
layout: full.html.hbs
algolia: true
title: updateMyCredentials
---


# updateMyCredentials

{{{since "1.0.0"}}}

Updates the credentials of the currently logged in user.


## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy to update


## Response

The result content depends on the authentication strategy. 

Example with the "local" authentication strategy:

```js
{
  "status": 200,
  "error": null,
  "action": "updateMyCredentials",
  "controller": "auth",
  "result": {
    "username": "MyUser",
    "kuid": "<kuid>"
  }
}
```
