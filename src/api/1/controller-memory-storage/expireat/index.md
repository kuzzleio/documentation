---
layout: full.html.hbs
algolia: true
title: expireat
---


# expireat

{{{since "1.0.0"}}}

Sets an expiration timestamp on a key. After the timestamp has been reached, the key will automatically be deleted.

The `timestamp` parameter accepts an [Epoch time](https://en.wikipedia.org/wiki/Unix_time) value.

[[_Redis documentation_]](https://redis.io/commands/expireat)


## Arguments

* `_id`: key to update


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "expireat",
  "collection": null,
  "index": null,
  "result": 1
}
```
