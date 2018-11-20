---
layout: full.html.hbs
algolia: true
title: lset
---


# lset

{{{since "1.0.0"}}}

Sets the list element at `index` with the provided value.

[[_Redis documentation_]](https://redis.io/commands/lset)


## Argument

* `_id`: list key identifier


## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "lset",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
