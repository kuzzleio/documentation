---
layout: full.html.hbs
algolia: true
title: lrange
---


# lrange

{{{since "1.0.0"}}}

Returns the list elements between the `start` and `stop` positions.

Offsets start at `0`, and the range is inclusive.

[[_Redis documentation_]](https://redis.io/commands/lrange)



## Argument

* `_id`: list key identifier
* `start`: starting index
* `stop`: ending index

The arguments `start` and `stop` can be negative. In that case, the offset is calculated from the end of the list, going backward. For instance, `-3` is the third element from the end of the list.

---

## Response

Returns an array of list elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lrange",
  "collection": null,
  "index": null,
  "result": [
    "value1",
    "value2",
    "..."
  ]
}
```
