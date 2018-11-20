---
layout: full.html.hbs
algolia: true
title: persist
---


# persist

{{{since "1.0.0"}}}

Removes the expiration delay or timestamp from a key, making it persistent.

[[_Redis documentation_]](https://redis.io/commands/persist)


## Argument

* `_id`: key to persist

---

## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "persist",
  "collection": null,
  "index": null,
  "result": 1
}
```
