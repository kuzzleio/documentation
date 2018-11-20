---
layout: full.html.hbs
algolia: true
title: lpop
---


# lpop

{{{since "1.0.0"}}}

Removes and returns the first element of a list.

[[_Redis documentation_]](https://redis.io/commands/lpop)


## Argument

* `_id`: list key identifier

---

## Response

Returns the removed value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lpop",
  "collection": null,
  "index": null,
  "result": "foo"
}
```
