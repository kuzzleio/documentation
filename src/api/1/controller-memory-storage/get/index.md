---
layout: full.html.hbs
algolia: true
title: get
---


# get

{{{since "1.0.0"}}}

Gets the value of a key.

[[_Redis documentation_]](https://redis.io/commands/get)


## Arguments

* `_id`: key to fetch

---

## Response

Returns the queried key's value. If the key doesn't exist, `get` returns `null`.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "get",
  "collection": null,
  "index": null,
  "result": "value"
}
```
