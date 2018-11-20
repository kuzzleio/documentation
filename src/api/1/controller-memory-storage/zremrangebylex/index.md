---
layout: full.html.hbs
algolia: true
title: zremrangebylex
---


# zremrangebylex

{{{since "1.0.0"}}}

Removes members within a provided range, from a sorted set where all elements have the same score, using lexicographical ordering. 

[[_Redis documentation_]](https://redis.io/commands/zremrangebylex)


## Arguments

* `_id`: sorted set identifier


## Response

Returns the number of removed members.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zremrangebylex",
  "collection": null,
  "index": null,
  "result": 14
}
```
