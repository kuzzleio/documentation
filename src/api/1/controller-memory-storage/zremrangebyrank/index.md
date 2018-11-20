---
layout: full.html.hbs
algolia: true
title: zremrangebyrank
---


# zremrangebyrank

{{{since "1.0.0"}}}

Removes members from a sorted set, with their position in the set within a provided index range.

Positions are 0-based, meaning the first member of the set has a position of 0.

[[_Redis documentation_]](https://redis.io/commands/zremrangebyrank)


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
  "action": "zremrangebyrank",
  "collection": null,
  "index": null,
  "result": 3
}
```
