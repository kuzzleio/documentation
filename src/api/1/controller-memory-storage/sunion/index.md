---
layout: full.html.hbs
algolia: true
title: sunion
---


# sunion

{{{since "1.0.0"}}}

Returns the union of sets of unique values.

[[_Redis documentation_]](https://redis.io/commands/sunion)


## Arguments

* `keys`: array of set identifiers

---

## Response

Returns the result of the union between the provided sets.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sunion",
  "collection": null,
  "index": null,
  "result": [
    "value1",
    "value2",
    "..."
  ]
}
```
