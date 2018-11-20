---
layout: full.html.hbs
algolia: true
title: ttl
---


# ttl

{{{since "1.0.0"}}}

Returns the remaining time to live of a key, in seconds.

[[_Redis documentation_]](https://redis.io/commands/ttl)



## Arguments

* `_id`: key identifier

---

## Response

Returns the remaining key TTL, in seconds, or a negative value if the key does not exist or if it is persistent.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "ttl",
  "collection": null,
  "index": null,
  "result": 76
}
```
