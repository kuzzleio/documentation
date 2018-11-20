---
layout: full.html.hbs
algolia: true
title: hexists
---


# hexists

{{{since "1.0.0"}}}

Checks if a field exists in a hash.

[[_Redis documentation_]](https://redis.io/commands/hexists)


## Arguments

* `_id`: hash key identifier
* `field`: field name to check

---

## Response

Returns either `0` (the field does not exist), or `1` (the field exist).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hexists",
  "collection": null,
  "index": null,
  "result": [0|1]
}
```
