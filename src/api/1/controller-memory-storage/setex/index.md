---
layout: full.html.hbs
algolia: true
title: setex
---


# setex

{{{since "1.0.0"}}}

Sets a value and a time to live (in seconds) on a key. If the key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/setex)


## Argument

* `_id`: key identifier


## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "setex",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
