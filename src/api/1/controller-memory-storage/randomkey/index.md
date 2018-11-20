---
layout: full.html.hbs
algolia: true
title: randomkey
---


# randomkey

{{{since "1.0.0"}}}

Returns a key identifier from the memory storage, at random.

[[_Redis documentation_]](https://redis.io/commands/randomkey)


## Response

Returns one of the database key, at random.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "randomkey",
  "collection": null,
  "index": null,
  "result": "qux"
}
```
