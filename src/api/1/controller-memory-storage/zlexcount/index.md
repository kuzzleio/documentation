---
layout: full.html.hbs
algolia: true
title: zlexcount
---


# zlexcount

{{{since "1.0.0"}}}

Counts elements in a sorted set where all members have equal score, using lexicographical ordering. 

[[_Redis documentation_]](https://redis.io/commands/zlexcount)


## Arguments

* `_id`: sorted set identifier
* `min`: range minimum value
* `max`: range maximum value

The `min` and `max` values are inclusive by default. To change this behavior, check the syntax detailed in the [Redis documentation](https://redis.io/commands/zrangebylex).

---

## Response

Returns the number of elements in the sorted set included in the provided range.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zlexcount",
  "collection": null,
  "index": null,
  "result": 3
}
```
