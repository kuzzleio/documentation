---
layout: full.html.hbs
algolia: true
title: zscore
---


# zscore

{{{since "1.0.0"}}}

Returns the score of an element in a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zscore)


## Arguments

* `_id`: sorted set identifier
* `member`: member value to examine

---

## Response

Returns the member score.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zscore",
  "collection": null,
  "index": null,
  "result": 23
}
```
