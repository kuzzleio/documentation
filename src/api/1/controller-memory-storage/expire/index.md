---
layout: full.html.hbs
algolia: true
title: expire
---



# expire

{{{since "1.0.0"}}}

Sets a timeout (in seconds) on a key. After the timeout has expired, the key is automatically deleted.

[[_Redis documentation_]](https://redis.io/commands/expire)


## Arguments

* `_id`: key to update


## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "expire",
  "collection": null,
  "index": null,
  "result": 1
}
```
