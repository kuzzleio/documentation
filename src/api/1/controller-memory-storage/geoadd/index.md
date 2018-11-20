---
layout: full.html.hbs
algolia: true
title: geoadd
---


# geoadd

{{{since "1.0.0"}}}

Adds geospatial points to the specified key.

[[_Redis documentation_]](https://redis.io/commands/geoadd)


## Arguments

* `_id`: key to update


## Response

Returns the number of points added to the key.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "geoadd",
  "collection": null,
  "index": null,
  "result": 2
}
```
