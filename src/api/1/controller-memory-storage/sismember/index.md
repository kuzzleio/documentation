---
layout: full.html.hbs
algolia: true
title: sismember
---


# sismember

{{{since "1.0.0"}}}

Checks if a value is a member of a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/sismember)


## Argument

* `_id`: set key identifier
* `member`: member value to check

---

## Response

Returns either `1` (member belongs to the set), or `0`.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sismember",
  "collection": null,
  "index": null,
  "result": 1
}
```
