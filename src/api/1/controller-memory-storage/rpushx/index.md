---
layout: full.html.hbs
algolia: true
title: rpushx
---


# rpushx

{{{since "1.0.0"}}}

Appends a value at the end of a list, only if the destination key already exists, and if it holds a list.

[[_Redis documentation_]](https://redis.io/commands/rpushx)


## Argument

* `_id`: list key identifier


## Response

Returns the updated list length.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "rpushx",
  "collection": null,
  "index": null,
  "result": 12
}
```
