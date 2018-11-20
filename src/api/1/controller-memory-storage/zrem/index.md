---
layout: full.html.hbs
algolia: true
title: zrem
---


# zrem

{{{since "1.0.0"}}}

Removes members from a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zrem)


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
  "action": "zrem",
  "collection": null,
  "index": null,
  "result": 3
}
```
