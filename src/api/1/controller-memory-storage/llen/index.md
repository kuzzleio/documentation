---
layout: full.html.hbs
algolia: true
title: llen
---


# llen

{{{since "1.0.0"}}}

Returns the length of a list.

[[_Redis documentation_]](https://redis.io/commands/llen)


## Argument

* `_id`: list key identifier

---

## Response

Returns the length of the list.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "llen",
  "collection": null,
  "index": null,
  "result": 7
}
```
