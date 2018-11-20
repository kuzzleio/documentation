---
layout: full.html.hbs
algolia: true
title: pfadd
---


# pfadd

{{{since "1.0.0"}}}

Adds elements to a [HyperLogLog](https://en.wikipedia.org/wiki/HyperLogLog) data structure.

[[_Redis documentation_]](https://redis.io/commands/pfadd)


## Argument

* `_id`: hyperloglog key identifier


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "pfadd",
  "collection": null,
  "index": null,
  "result": 1
}
```
