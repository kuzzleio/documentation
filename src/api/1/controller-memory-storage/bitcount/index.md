---
layout: full.html.hbs
algolia: true
title: bitcount
---


# bitcount

{{{since "1.0.0"}}}

Counts the number of set bits (population counting) in a string.  

[[_Redis documentation_]](https://redis.io/commands/bitcount)


## Arguments

* `_id`: key to evaluate

### Optional:

* `start`: count starts at the provided offset
* `end`: count ends at the provided offset

---

## Response

Returns the number of set bits.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "bitcount",
  "collection": null,
  "index": null,
  "result": 42
}
```
