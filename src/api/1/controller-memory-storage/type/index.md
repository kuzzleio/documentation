---
layout: full.html.hbs
algolia: true
title: type
---


# type

{{{since "1.0.0"}}}

Returns the type of the value held by a key.

[[_Redis documentation_]](https://redis.io/commands/type)


## Arguments

* `_id`: key identifier

---

## Response

Returns one of the following: `hash`, `list`, `string`, `set`, `zset`.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "type",
  "collection": null,
  "index": null,
  "result": "list"
}
```
