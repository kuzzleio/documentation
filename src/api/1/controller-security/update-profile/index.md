---
layout: full.html.hbs
algolia: true
title: updateProfile
---


# updateProfile

{{{since "1.0.0"}}}

Updates a security profile definition.


## Arguments

* `_id`: profile identifier

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the profile changes are indexed


## Response

Returns the updated profile identifier and version number.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "updateProfile",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<profileId>",
    "_version": 2
  }
}
```
