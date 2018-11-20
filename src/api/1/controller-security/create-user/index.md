---
layout: full.html.hbs
algolia: true
title: createUser
---


# createUser

{{{since "1.0.0"}}}

Creates a new user.

The body contains the user data and must have the following properties:


## Arguments

### Optional:

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier). An error is returned if the provided identifier already exists. If not provided, a random kuid is automatically generated.


## Response

Returns the user creation status:

* `_id`: new user kuid
* `_source`: new user content and attributed profiles
* `created`: always true
* `version`: always 1

```javascript
{
  "status": 200,
  "error": null,
  "controller": "security",
  "action": "createUser",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "profileIds": ["<profileId>"],
      "fullname": "John Doe"
    },
    "_version": 1,
    "created": true
  }
}
```
