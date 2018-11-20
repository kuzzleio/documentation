---
layout: full.html.hbs
algolia: true
title: createOrReplaceProfile
---


# createOrReplaceProfile

{{{since "1.0.0"}}}

Creates a new profile or, if the provided profile identifier already exists, replaces it.


## Arguments

* `_id`: profile identifier

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the created/replaced profile is indexed


## Response

Returns an object with the new profile modification status:

* `_id`: created/replaced profile identifier
* `_source`: profile content
* `created`: if `true`, the profile has been created. Otherwise, it has been replaced
* `version`: updated version number of the profile

```javascript
{
  "status": 200,
  "error": null,
  "result": {
    "_id": "<profileId>",
    "_version": 1,
    "_source": {
      // new profile content
    }
    "created": true
  },
  "requestId": "<request unique identifier>",
  "controller": "security",
  "action": "createOrReplaceProfile"
}
```
