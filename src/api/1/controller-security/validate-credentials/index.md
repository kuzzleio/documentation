---
layout: full.html.hbs
algolia: true
title: validateCredentials
---


# validateCredentials

{{{since "1.0.0"}}}

Checks if the provided credentials are well-formed. Does not actually save credentials.


## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) 
* `strategy`: authentication strategy


## Response

Returns a boolean telling whether credentials are valid.

```javascript
{
  "status": 200,
  "error": null,
  "action": "validateCredentials",
  "controller": "security",
  "_id": "<kuid>",
  "result": true
}
```
