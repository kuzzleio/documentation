---
layout: full.html.hbs
algolia: true
title: validateMyCredentials
---


# validateMyCredentials

{{{since "1.0.0"}}}

Validates the provided credentials against a specified authentication strategy. 

This route neither creates nor modifies credentials. 


## Arguments

* `jwt`: valid authentication token (for the HTTP protocol, the token is to be passed to the `Authorization` header instead)
* `strategy`: name of the authentication strategy used to validate the provided credentials


## Response

Returns a boolean telling whether the provided credentials are valid:

```js
{
  "status": 200,
  "error": null,
  "action": "validateMyCredentials",
  "controller": "auth",
  "result": true
}
```
