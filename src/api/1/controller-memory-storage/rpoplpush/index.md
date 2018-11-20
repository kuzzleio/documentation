---
layout: full.html.hbs
algolia: true
title: rpoplpush
---


# rpoplpush

{{{since "1.0.0"}}}

Removes the last element of a list, and pushes it back at the start of another list.

[[_Redis documentation_]](https://redis.io/commands/rpoplpush)


## Response

Returns the popped/pushed element.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "rpoplpush",
  "collection": null,
  "index": null,
  "result": "<value>"
}
```
