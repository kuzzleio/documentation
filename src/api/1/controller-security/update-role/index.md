---
layout: full.html.hbs
algolia: true
title: updateRole
---


# updateRole

{{{since "1.0.0"}}}

Updates a security role definition.

**Note:** partial updates are not supported for roles, this API route will replace the entire role content with the provided one.


## Arguments

* `_id`: role identifier

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the role changes are indexed


## Response

Returns the updated role identifier and version number.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "action": "updateRole",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<roleId>",
    "_version": 2
  }
}
```
