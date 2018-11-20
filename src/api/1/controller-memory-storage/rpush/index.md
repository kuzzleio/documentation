---
layout: full.html.hbs
algolia: true
title: rpush
---


# rpush

{{{since "1.0.0"}}}

Appends values at the end of a list. 

If the destination list does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/rpush)



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
  "action": "rpush",
  "collection": null,
  "index": null,
  "result": 12
}
```
