---
layout: full.html.hbs
algolia: true
title: smove
---


# smove

{{{since "1.0.0"}}}

Moves a member from a set of unique values to another.

[[_Redis documentation_]](https://redis.io/commands/smove)


## Argument

* `_id`: source set identifier


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "smove",
  "collection": null,
  "index": null,
  "result": 1
}
```
