---
layout: full.html.hbs
algolia: true
title: renamenx
---


# renamenx

{{{since "1.0.0"}}}

Renames a key, only if the new name is not already used.

[[_Redis documentation_]](https://redis.io/commands/renamenx)


## Argument

* `_id`: key identifier


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "renamenx",
  "collection": null,
  "index": null,
  "result": 1
}
```
