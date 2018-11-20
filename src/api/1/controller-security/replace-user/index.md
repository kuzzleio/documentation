---
layout: full.html.hbs
algolia: true
title: replaceUser
---


# replaceUser

{{{since "1.0.0"}}}

Replaces a user with new configuration.


## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier)

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the user replacement is indexed


## Response

Returns the user replacement status:

* `_id`: new user kuid
* `_source`: new user content and attributed profiles

```javascript
{
  "status": 200,
  "error": null,
  "action": "replaceUser",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "profileIds": ["<profileId>"],
      "fullname": "John Doe"
    }
  }
}
```
