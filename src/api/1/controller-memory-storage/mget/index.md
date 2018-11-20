---
layout: full.html.hbs
algolia: true
title: mget
---


# mget

{{{since "1.0.0"}}}

Returns the values of the provided keys.

[[_Redis documentation_]](https://redis.io/commands/mget)


## Argument

* `keys`: a list of keys to get

---

## Response

Returns the list of corresponding key values, in the same order than the one provided in the query.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "mget",
  "collection": null,
  "index": null,
  "result": [
    "value of key1",
    "value of key2",
    "..."
  ]
}
```
