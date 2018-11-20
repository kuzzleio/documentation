---
layout: full.html.hbs
algolia: true
title: adminExists
---


# adminExists

{{{since "1.0.0"}}}

Checks that an administrator account exists.


## Response

Returns an `exists` boolean telling whether an administrator account exists.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "index": null,
  "collection": null,
  "action": "adminExists",
  "controller": "server",
  "requestId": "<unique request identifier>",
  "result": {
    "exists": true
  }
}
```
