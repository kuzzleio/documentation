---
layout: full.html.hbs
algolia: true
title: zremrangebyscore
---


# zremrangebyscore

{{{since "1.0.0"}}}

Removes members from a sorted set, with a score between the provided interval.

[[_Redis documentation_]](https://redis.io/commands/zremrangebylex)


## Arguments

* `_id`: sorted set identifier


## Response

Returns the number of removed elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zremrangebyscore",
  "collection": null,
  "index": null,
  "result": 16
}
```
