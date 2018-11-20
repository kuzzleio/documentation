---
layout: full.html.hbs
algolia: true
title: rename
---


# rename

{{{since "1.0.0"}}}

Renames a key.

If the new key name is already used, then it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/rename)


## Argument

* `_id`: key to rename


## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "rename",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
