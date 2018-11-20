---
layout: full.html.hbs
algolia: true
title: zrange
---


# zrange

{{{since "1.0.0"}}}

Returns elements depending on their position in the sorted set.

[[_Redis documentation_]](https://redis.io/commands/zrange)


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
    "element1",
    "element2",
    "..."
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
    "element1",
    "score of element1",
    "element2",
    "score of element2",
    "..."
  ]
}
```
