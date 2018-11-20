---
layout: full.html.hbs
algolia: true
title: zrangebylex
---


# zrangebylex

{{{since "1.0.0"}}}

Returns elements within a provided interval, in a sorted set where all members have equal score, using lexicographical ordering. 

[[_Redis documentation_]](https://redis.io/commands/zrangebylex)


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
  "action": "zrangebylex",
  "collection": null,
  "index": null,
  "result": [
    "element1",
    "element2",
    "..."
  ]
}
```
