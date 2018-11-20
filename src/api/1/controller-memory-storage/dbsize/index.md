---
layout: full.html.hbs
algolia: true
title: dbsize
---


# dbsize

{{{since "1.0.0"}}}

Returns the number of keys in the application database.

[[_Redis documentation_]](https://redis.io/commands/dbsize)


## Response

Returns the number of found keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "dbsize",
  "collection": null,
  "index": null,
  "result": 42
}
```
