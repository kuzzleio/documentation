---
layout: full.html.hbs
algolia: true
title: touch
---


# touch

{{{since "1.0.0"}}}

Alters the last access time of one or multiple keys. A key is ignored if it does not exist.

[[_Redis documentation_]](https://redis.io/commands/touch)


## Arguments

* `keys`: array of key identifiers to alter

---

## Response

Returns the number of altered keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "touch",
  "collection": null,
  "index": null,
  "result": 3
}
```
