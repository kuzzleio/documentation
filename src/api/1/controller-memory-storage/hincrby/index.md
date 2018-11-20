---
layout: full.html.hbs
algolia: true
title: hincrby
---


# hincrby

{{{since "1.0.0"}}}

Increments the number stored in a hash field by the provided integer value.

[[_Redis documentation_]](https://redis.io/commands/hincrby)


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
  "action": "hincrby",
  "collection": null,
  "index": null,
  "result": 42
}
```
