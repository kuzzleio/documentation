---
layout: full.html.hbs
algolia: true
title: sdiffstore
---


# sdiffstore

{{{since "1.0.0"}}}

Computes the difference between a reference set of unique values, and other sets. The differences are then stored in the provided destination key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sdiffstore)


## Argument

* `_id`: reference set identifier


## Response

Returns the number of elements stored in the resulting set.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sdiffstore",
  "collection": null,
  "index": null,
  "result": 4
}
```
