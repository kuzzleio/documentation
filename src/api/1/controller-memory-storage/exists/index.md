---
layout: full.html.hbs
algolia: true
title: exists
---


# exists

{{{since "1.0.0"}}}

Checks if the specified keys exist in the database.

[[_Redis documentation_]](https://redis.io/commands/exists)


## Arguments

* `keys`: list of keys to verify

---

## Response

Returns the number of existing keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "exists",
  "collection": null,
  "index": null,
  "result": 1
}
```
