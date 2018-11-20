---
layout: full.html.hbs
algolia: true
title: hasCredentials
---


# hasCredentials

{{{since "1.0.0"}}}

Checks if a user has credentials registered for the specified authentication strategy.


## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier) 
* `strategy`: authentication strategy

---

## Response

Returns a boolean telling whether the user can log in using the provided authentication strategy.

```javascript
{
  "status": 200,
  "error": null,
  "action": "hasCredentials",
  "controller": "security",
  "_id": "<kuid>",
  "result": true
}
```
