---
layout: full.html.hbs
algolia: true
title: getUser
---


# getUser

{{{since "1.0.0"}}}

Gets a user.


## Arguments

* `_id`: user [kuid]({{site_base_path}}guide/1/kuzzle-depth/authentication/#the-kuzzle-user-identifier)

---

## Response

Returns the user information:

* `_id`: user kuid
* `_source`: user description

```javascript
{
  "status": 200,
  "error": null,
  "controller": "security",
  "action": "getUser",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",
    "_source": {
      "profileIds": ["<profileId>"]
    }
  }
}
```
