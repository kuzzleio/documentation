---
layout: full.html.hbs
algolia: true
title: zrevrange
---


# zrevrange

{{{since "1.0.0"}}}

Identical to [zrange]({{ site_base_path }}api/1/controller-memory-storage/zrange), except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrange)


## Arguments

* `_id`: sorted set identifier
* `start`: starting position index, inclusive
* `stop`: ending position index, inclusive

### Optional:

* `withscores`: return the score alongside the found elements

---

## Response

By default, returns the list of elements in the provided index range:

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zrange",
  "collection": null,
  "index": null,
  "result": [
    "...",
    "element2",
    "element1"
  ]
}
```

If the `withscores` option is provided, then the returned array alternates elements with their score:

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zrange",
  "collection": null,
  "index": null,
  "result": [
    "...",
    "element2",
    "score of element2",
    "element1",
    "score of element1"
  ]
}
```
