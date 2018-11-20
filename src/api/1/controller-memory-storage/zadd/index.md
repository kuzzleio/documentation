---
layout: full.html.hbs
algolia: true
title: zadd
---


# zadd

{{{since "1.0.0"}}}

Adds elements to a sorted set. 

If the key does not exist, it is created, holding an empty sorted set. 

If the key already exists but does not hold a sorted set, an error is returned.

If a member to insert is already in the sorted set, its score is updated and the member is reinserted at the right position in the set.

[[_Redis documentation_]](https://redis.io/commands/zadd)


## Arguments

* `_id`: sorted set identifier


## Response

Returns the number of added elements.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zadd",
  "collection": null,
  "index": null,
  "result": 6
}
```
