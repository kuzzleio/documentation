---
layout: full.html.hbs
algolia: true
title: now
---


# now

{{{since "1.0.0"}}}

Returns the current server timestamp, in Epoch-millis format.


## Response

Returns a `now` property containing the server timestamp, in Epoch-millis format.

```javascript
{
  "status": 200,                     
  "error": null,                     
  "controller": "server",
  "action": "now",
  "requestId": "<unique request identifier>",
  "result": {
    "now": 1447151167622
  }
}
```
