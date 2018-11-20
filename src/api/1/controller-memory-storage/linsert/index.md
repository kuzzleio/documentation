---
layout: full.html.hbs
algolia: true
title: linsert
---


# linsert

{{{since "1.0.0"}}}

Inserts a value in a list, either before or after a pivot value.

[[_Redis documentation_]](https://redis.io/commands/linsert)


## Argument

* `_id`: list key identifier


## Response

Returns the updated length of the list.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "linsert",
  "collection": null,
  "index": null,
  "result": 7
}
```
