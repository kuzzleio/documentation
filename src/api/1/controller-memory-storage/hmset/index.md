---
layout: full.html.hbs
algolia: true
title: hmset
---


# hmset

{{{since "1.0.0"}}}

Sets multiple fields at once in a hash.

[[_Redis documentation_]](https://redis.io/commands/hmset)


## Arguments

* `_id`: hash key identifier


## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "hmset",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
