---
layout: full.html.hbs
algolia: true
title: ping
---


# ping

{{{since "1.0.0"}}}

Pings the memory storage database.

[[_Redis documentation_]](https://redis.io/commands/ping)


## Response

Returns a `"PONG"` response.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "ping",
  "collection": null,
  "index": null,
  "result": "PONG"
}
```
