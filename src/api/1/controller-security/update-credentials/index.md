---
layout: full.html.hbs
algolia: true
title: updateCredentials
---


# updateCredentials

{{{since "1.0.0"}}}

Updates a user credentials for the specified authentication strategy.


## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) 
* `strategy`: authentication strategy


## Response

Returns the authentication strategy response.

### Example for the "local" authentication strategy:

```javascript
{
  "status": 200,
  "error": null,
  "action": "updateCredentials",
  "controller": "security",
  "_id": "<kuid>",
  "result": {
    "username": "MyUser",
    "kuid": "<kuid>"
  }
}
```
