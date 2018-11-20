---
layout: full.html.hbs
algolia: true
title: zcard
---


# zcard

{{{since "1.0.0"}}}

Returns the number of elements held by a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zcard)


## Arguments

* `_id`: sorted set identifier

---

## Response

Returns the number of members in the sorted set.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zcard",
  "collection": null,
  "index": null,
  "result": 10
}
```
