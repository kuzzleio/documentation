---
layout: full.html.hbs
algolia: true
title: pexpire
---


# pexpire

{{{since "1.0.0"}}}

Sets a timeout (in milliseconds) on a key. After the timeout has expired, the key will automatically be deleted.

[[_Redis documentation_]](https://redis.io/commands/pexpire)


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
  "action": "pexpire",
  "collection": null,
  "index": null,
  "result": 1
}
```
