---
layout: full.html.hbs
algolia: true
title: ping
---

# ping

{{{since "1.0.0"}}}

Ping the memory storage database.

[[_Redis documentation_]](https://redis.io/commands/ping)

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/ms/_ping
Method: GET
```

### Other protocols

```js
{
  "controller": "ms",
  "action": "ping"
}
```

---

## Response

Return a `"PONG"` response.

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
