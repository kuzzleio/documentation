---
layout: full.html.hbs
algolia: true
title: hmget
---


# hmget

{{{since "1.0.0"}}}

Returns the values of the specified hash's fields.

[[_Redis documentation_]](https://redis.io/commands/hmget)


## Arguments

* `_id`: hash key identifier
* `fields`: the list of fields to fetch

---

## Response

Returns the list of requested field values, in the same order than in the query.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hmget",
  "collection": null,
  "index": null,
  "result": [
    "field1's value",
    "field2's value",
    "...'s value"
  ]
}
```
