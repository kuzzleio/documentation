---
layout: full.html.hbs
algolia: true
title: incrbyfloat
---


# incrbyfloat

{{{since "1.0.0"}}}

Increments the number stored at `key` by the provided float value. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/incrbyfloat)


## Arguments

* `_id`: key identifier


## Response

Returns the incremented float value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "incrbyfloat",
  "collection": null,
  "index": null,
  "result": "3.1415"
}
```
