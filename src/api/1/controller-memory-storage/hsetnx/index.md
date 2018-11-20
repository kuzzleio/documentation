---
layout: full.html.hbs
algolia: true
title: hsetnx
---


# hsetnx

{{{since "1.0.0"}}}

Sets a field and its value in a hash, only if the field does not already exist.

[[_Redis documentation_]](https://redis.io/commands/hsetnx)


## Arguments

* `_id`: hash key identifier


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hsetnx",
  "collection": null,
  "index": null,
  "result": 1
}
```
