---
layout: full.html.hbs
algolia: true
title: createFirstAdmin
---


# createFirstAdmin

{{{since "1.0.0"}}}

Creates a Kuzzle administrator account, only if none exist.


## Arguments

### Optional:

* `_id`: specify the administror [kuid]({{ site_base_path }}guide/1/essentials/user-authentication/#kuzzle-user-identifier-kuid), instead of letting Kuzzle generate a random identifier.
* `reset` (boolean): if true, restricted rights are applied to the `anonymous` and `default` roles (by default, these roles don't have any restriction). 


## Response

Returns information about the newly created administrator:

* `_id`: administrator kuid
* `_source`: administrator user document, contains all properties set in the `content` body argument, but also the list of attributed `profileIds`. That list is initialized with the `admin` profile

```javascript
{
  "status": 200,                     
  "error": null,                     
  "controller": "security",
  "action": "createFirstAdmin",
  "volatile": {},
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<kuid>",                  // The kuzzle user identifier
    "_source": {
      "name": "John Doe",
      "profileIds": [
        "admin"
      ]
    }
  }
}
```
