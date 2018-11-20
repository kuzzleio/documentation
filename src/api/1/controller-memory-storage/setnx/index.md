---
layout: full.html.hbs
algolia: true
title: setnx
---


# setnx

{{{since "1.0.0"}}}

Sets a value on a key, only if it does not already exist.

[[_Redis documentation_]](https://redis.io/commands/setnx)


## Argument

* `_id`: key identifier


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "setnx",
  "collection": null,
  "index": null,
  "result": 1
}
```
