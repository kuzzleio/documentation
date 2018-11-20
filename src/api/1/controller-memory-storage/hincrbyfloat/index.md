---
layout: full.html.hbs
algolia: true
title: hincrbyfloat
---


# hincrbyfloat

{{{since "1.0.0"}}}

Increments the number stored in a hash field by the provided float value.

[[_Redis documentation_]](https://redis.io/commands/hincrbyfloat)


## Arguments

* `_id`: hash key identifier


## Response

Returns the updated value for the incremented hash field.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hincrbyfloat",
  "collection": null,
  "index": null,
  "result": "3.14159"
}
```

Increments the number stored in a hash field by the provided float value.

[[_Redis documentation_]](https://redis.io/commands/hincrbyfloat)
