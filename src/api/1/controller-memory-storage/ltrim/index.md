---
layout: full.html.hbs
algolia: true
title: ltrim
---


# ltrim

{{{since "1.0.0"}}}

Trims an existing list so that it will contain only the specified range of elements specified.

[[_Redis documentation_]](https://redis.io/commands/ltrim)


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
  "action": "ltrim",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
