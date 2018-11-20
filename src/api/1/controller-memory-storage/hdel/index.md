---
layout: full.html.hbs
algolia: true
title: hdel
---



# hdel

{{{since "1.0.0"}}}

Removes fields from a hash.

[[_Redis documentation_]](https://redis.io/commands/hdel)


## Arguments

* `_id`: hash key identifier


## Response

Returns the number of removed fields.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hdel",
  "collection": null,
  "index": null,
  "result": 3
}
```
