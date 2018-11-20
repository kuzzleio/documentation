---
layout: full.html.hbs
algolia: true
title: pttl
---


# pttl

{{{since "1.0.0"}}}

Returns the remaining time to live of a key, in milliseconds.

[[_Redis documentation_]](https://redis.io/commands/pttl)



## Argument

* `_id`: key identifier

---

## Response

Returns the remaining TTL, in milliseconds.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "pttl",
  "collection": null,
  "index": null,
  "result": 43728
}
```
