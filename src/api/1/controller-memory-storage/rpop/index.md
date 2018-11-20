---
layout: full.html.hbs
algolia: true
title: rpop
---


# rpop

{{{since "1.0.0"}}}

Removes the last element of a list and returns it.

[[_Redis documentation_]](https://redis.io/commands/rpop)


## Argument

* `_id`: list key identifier

---

## Response

Returns the removed element.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "rpop",
  "collection": null,
  "index": null,
  "result": "bar"
}
```
