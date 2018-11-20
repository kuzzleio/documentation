---
layout: full.html.hbs
algolia: true
title: hkeys
---


# hkeys

{{{since "1.0.0"}}}

Returns all field names contained in a hash.

[[_Redis documentation_]](https://redis.io/commands/hkeys)


## Arguments

* `_id`: hash key identifier

---

## Response

Returns an array of hash field names.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hkeys",
  "collection": null,
  "index": null,
  "result": [
    "field1",
    "field2",
    "..."
  ]
}
```
