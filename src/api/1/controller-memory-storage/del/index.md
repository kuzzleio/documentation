---
layout: full.html.hbs
algolia: true
title: del
---


# del

{{{since "1.0.0"}}}

Deletes a list of keys.

[[_Redis documentation_]](https://redis.io/commands/del)


## Body properties

* `keys`: an array of keys to delete

---

## Response

Returns the number of deleted keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "del",
  "collection": null,
  "index": null,
  "result": 3
}
```
