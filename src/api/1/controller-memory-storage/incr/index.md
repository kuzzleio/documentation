---
layout: full.html.hbs
algolia: true
title: incr
---


# incr

{{{since "1.0.0"}}}

Increments the number stored at `key` by 1. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/incr)


## Arguments

* `_id`: key identifier

---

## Response

Returns the incremented key value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "incr",
  "collection": null,
  "index": null,
  "result": 6
}
```
