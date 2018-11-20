---
layout: full.html.hbs
algolia: true
title: getbit
---


# getbit

{{{since "1.0.0"}}}

Returns the bit value at the provided offset, in the string value stored in a key.

[[_Redis documentation_]](https://redis.io/commands/getbit)


## Arguments

* `_id`: key containing the geopoints to fetch
* `offset`: bit offset to return

---

## Response

Returns the bit at the provided offset. The returned value can be either `0` or `1`.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "getbit",
  "collection": null,
  "index": null,
  "result": 0
}
```
