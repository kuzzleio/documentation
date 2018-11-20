---
layout: full.html.hbs
algolia: true
title: sadd
---


# sadd

{{{since "1.0.0"}}}

Adds members to a set of unique values stored at `key`. 

If the destination set does not exist, it is created beforehand.

[[_Redis documentation_]](https://redis.io/commands/sadd)


## Argument

* `_id`: set key identifier


## Response

Returns the number of elements successfully added to the set.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sadd",
  "collection": null,
  "index": null,
  "result": 2
}
```
