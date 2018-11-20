---
layout: full.html.hbs
algolia: true
title: mDeleteUsers
---


# mDeleteUsers

{{{since "1.0.0"}}}

Deletes multiple users.


## Arguments

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the deletions are indexed


## Response

Returns an array of successfully deleted users.

```javascript
{
  "status": 200,
  "error": null,
  "action": "mDeleteUsers",
  "controller": "security",
  "requestId": "<unique request identifier>",
  "result": [
    "kuid1",
    "kuid2", 
    "..."
   ]
  }
}
```
