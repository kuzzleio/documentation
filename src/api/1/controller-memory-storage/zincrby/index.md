---
layout: full.html.hbs
algolia: true
title: zincrby
---


# zincrby

{{{since "1.0.0"}}}

Increments the score of a sorted set member by the provided value.

[[_Redis documentation_]](https://redis.io/commands/zincrby)


## Arguments

* `_id`: sorted set identifier


## Response

Returns the updated member's score value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zincrby",
  "collection": null,
  "index": null,
  "result": 26
}
```
