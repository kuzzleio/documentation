---
layout: full.html.hbs
algolia: true
title: hset
---


# hset

{{{since "1.0.0"}}}

Sets a field and its value in a hash. 

If the key does not exist, a new key holding a hash is created. 

If the field already exists, its value is overwritten.

[[_Redis documentation_]](https://redis.io/commands/hset)


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
  "action": "hset",
  "collection": null,
  "index": null,
  "result": 1
}
```
