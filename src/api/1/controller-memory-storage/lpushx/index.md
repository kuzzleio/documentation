---
layout: full.html.hbs
algolia: true
title: lpushx
---


# lpushx

{{{since "1.0.0"}}}

Prepends the specified value to a list, only if the key already exists and if it holds a list.

[[_Redis documentation_]](https://redis.io/commands/lpushx)


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
  "action": "lpushx",
  "collection": null,
  "index": null,
  "result": 2
}
```
