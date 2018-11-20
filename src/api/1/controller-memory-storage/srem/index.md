---
layout: full.html.hbs
algolia: true
title: srem
---


# srem

{{{since "1.0.0"}}}

Removes members from a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/srem)


## Argument

* `_id`: set key identifier


## Response

Returns the number of removed members.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "srem",
  "collection": null,
  "index": null,
  "result": 3
}
```
