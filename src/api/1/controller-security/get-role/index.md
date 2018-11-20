---
layout: full.html.hbs
algolia: true
title: getRole
---


# getRole

{{{since "1.0.0"}}}

Gets a security role.


## Arguments

* `_id`: role identifier

---

## Response

Returns the queried role:

* `_id`: role identifier
* `_source`: role description

```javascript
{
  "status": 200,                     
  "error": null,                     
  "result": {
    "_id": "<roleId>",
    "_source": {
      "controllers": {
        "*": {
          "actions": true
        }
      }
    }
  },
  "action": "getRole",
  "controller": "security",
  "requestId": "<unique request identifier>"
}
```
