---
layout: full.html.hbs
algolia: true
title: zinterstore
---


# zinterstore

{{{since "1.0.0"}}}

Computes the intersection of the provided sorted sets, and stores the result in a new sorted set.

[[_Redis documentation_]](https://redis.io/commands/zinterstore)


## Argument

* `_id`: sorted set to create/overwrite with the computed intersection


## Response

Returns the number of members added to the destination sorted set.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "zinterstore",
  "collection": null,
  "index": null,
  "result": 12
}
```
