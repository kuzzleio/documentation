---
layout: full.html.hbs
algolia: true
title: hget
---


# hget

{{{since "1.0.0"}}}

Returns the field's value of a hash.

[[_Redis documentation_]](https://redis.io/commands/hget)


## Arguments

* `_id`: hash key identifier
* `field`: field name to retrieve

---

## Response

Returns the field's value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hget",
  "collection": null,
  "index": null,
  "result": "field value"
}
```
