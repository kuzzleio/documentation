---
layout: full.html.hbs
algolia: true
title: mDeleteRoles
---


# mDeleteRoles

{{{since "1.0.0"}}}

Deletes multiple security roles.


## Arguments

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the deletions are indexed


## Response

Returns an array of successfully deleted roles.

```javascript
{
  "status": 200,
  "error": null,
  "action": "mDeleteRoles",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": [
    "role1",
    "role2",
    "..."
  ]
}
```
