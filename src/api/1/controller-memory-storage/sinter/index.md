---
layout: full.html.hbs
algolia: true
title: sinter
---


# sinter

{{{since "1.0.0"}}}

Returns the intersection of the provided sets of unique values.

[[_Redis documentation_]](https://redis.io/commands/sinter)


## Argument

* `keys`: list of set identifiers to intersect

---

## Response

Returns an array of intersected values.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sinter",
  "collection": null,
  "index": null,
  "result": [
    "value1",
    "value2",
    "..."
  ]
}
```
