---
layout: full.html.hbs
algolia: true
title: set
---


# set

{{{since "1.0.0"}}}

Creates a key holding the provided value, or overwrites it if it already exists.

[[_Redis documentation_]](https://redis.io/commands/set)


## Argument

* `_id`: key identifier


## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "set",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
