---
layout: full.html.hbs
algolia: true
title: createRole
---


# createRole

{{{since "1.0.0"}}}

Creates a new role.


## Arguments

* `_id`: role identifier

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the created role is indexed


## Response

Returns the role creation/replacement status:

* `_id`: created/replaced role identifier
* `_source`: role definition
* `created`: always true
* `version`: always 1

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result": {
    "_id": "<roleId>",
    "_version": 1,
    "created": true,
    "_source": {
      "controllers": {
        "*": {
          "actions": {
            "*": true
          }
        }
      }
    }
  }
  "requestId": "<unique request identifier>",
  "controller": "security",
  "action": "createRole"
}
```
