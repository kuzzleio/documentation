---
layout: full.html.hbs
algolia: true
title: deleteProfile
---



# deleteProfile

{{{since "1.0.0"}}}

Deletes a security profile.

An error is returned if the profile is still in use.


## Arguments

* `_id`: profile identifier

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the profile deletion is indexed

---

## Response

Returns the deleted profile identifier.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result": {
    "_id": "<profileId>"
  },
  "action": "deleteProfile",
  "controller": "security",
  "requestId": "<unique request identifier>"
}
```

