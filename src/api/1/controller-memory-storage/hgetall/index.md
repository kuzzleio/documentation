---
layout: full.html.hbs
algolia: true
title: hgetall
---


# hgetall

{{{since "1.0.0"}}}

Returns all fields and values of a hash.

[[_Redis documentation_]](https://redis.io/commands/hgetall)


## Arguments

* `_id`: hash key identifier

---

## Response

Returns the requested hash content as a `field: value` object.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hgetall",
  "collection": null,
  "index": null,
  "result": {
    "field1": "value",
    "field2": "value",
    "...": "..."
  }
}
```
