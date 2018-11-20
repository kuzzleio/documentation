---
layout: full.html.hbs
algolia: true
title: zrevrangebyscore
---


# zrevrangebyscore

{{{since "1.0.0"}}}

Identical to [zrangebyscore]({{ site_base_path }}api/1/controller-memory-storage/zrangebyscore), except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrangebyscore)


## Arguments:

* `_id`: sorted set identifier
* `min`: minimum score
* `max`: maximum score

By default, `min` and `max` are inclusive. Check the full Redis documentation for other options.

### Optional:

* `limit`: an array of 2 integers, used to limit the number of returned matching elements (similar to _SELECT LIMIT offset, count_ in SQL). Format: `[<offset>,<count>]` 
* `withscores`: return the score alongside the found elements

---

## Response

By default, returns the list of elements in the provided score range:

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zrevrangebyscore",
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
  "action": "zrevrangebyscore",
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
