---
layout: full.html.hbs
algolia: true
title: decr
---


# decr

{{{since "1.0.0"}}}

Decrements the number stored at `key` by 1. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/decr)


## Arguments

* `_id`: key to decrement

---

## Response

Returns the updated key value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "decr",
  "collection": null,
  "index": null,
  "result": -13
}
```
