---
layout: full.html.hbs
algolia: true
title: createProfile
---



# createProfile

{{{since "1.0.0"}}}

Creates a new profile.


## Arguments

* `_id`: new profile identifier. An error is returned if the profile already exists

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the created profile is indexed


## Response

Returns an object with the new profile creation status:

* `_id`: created profile identifier
* `_source`: profile content
* `created`: always `true`
* `version`: always `1`

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result": {
    "_id": "<profileId>",
    "_version": 1,
    "created": true,
    "_source": {
      // new profile content
    }
  },
  "requestId": "<unique request identifier>",
  "controller": "security",
  "action": "createProfile"
}
```
