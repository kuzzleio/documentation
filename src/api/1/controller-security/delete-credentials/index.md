---
layout: full.html.hbs
algolia: true
title: deleteCredentials
---


# deleteCredentials

{{{since "1.0.0"}}}

Deletes user credentials for the specified authentication strategy.


## Arguments

* `_id`: user [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid) 
* `strategy`: authentication strategy name to remove

---

## Response

Returns an acknowledgement.

```javascript
{
  "status": 200,
  "error": null,
  "action": "deleteCredentials",
  "controller": "security",
  "_id": "<kuid>",
  "result": {
    "acknowledged": true
  }
}
```
