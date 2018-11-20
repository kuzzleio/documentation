---
layout: full.html.hbs
algolia: true
title: zrevrangebylex
---


# zrevrangebylex

{{{since "1.0.0"}}}

Identical to [zrangebylex]({{ site_base_path }}api/1/controller-memory-storage/zrangebylex) except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrangebylex)


## Arguments

* `_id`: sorted set identifier
* `min`: minimum element value
* `max`: maximum element value

The `min` and `max` values are inclusive by default. To change this behavior, check the full Redis documentation.

### Optional:

* `limit`: an array of 2 integers, used to limit the number of returned matching elements (similar to _SELECT LIMIT offset, count_ in SQL). Format: `[<offset>,<count>]` 

---

## Response

Returns an array of matched elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zrevrangebylex",
  "collection": null,
  "index": null,
  "result": [
    "...",
    "element2",
    "element1"
  ]
}
```
