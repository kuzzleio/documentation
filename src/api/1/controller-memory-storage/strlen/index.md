---
layout: full.html.hbs
algolia: true
title: strlen
---


# strlen

{{{since "1.0.0"}}}

Returns the length of a value.

[[_Redis documentation_]](https://redis.io/commands/strlen)


## Arguments

* `_id`: key identifier holding a string value

---

## Response

Returns a string length.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "strlen",
  "collection": null,
  "index": null,
  "result": 6
}
```
