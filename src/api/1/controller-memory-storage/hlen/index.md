---
layout: full.html.hbs
algolia: true
title: hlen
---


# hlen

{{{since "1.0.0"}}}

Returns the number of fields contained in a hash.

[[_Redis documentation_]](https://redis.io/commands/hlen)


## Arguments

* `_id`: hash key identifier

---

## Response

Returns the number of fields stored in a hash.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hlen",
  "collection": null,
  "index": null,
  "result": 4
}
```
