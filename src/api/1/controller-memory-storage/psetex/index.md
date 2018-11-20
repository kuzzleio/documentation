---
layout: full.html.hbs
algolia: true
title: psetex
---


# psetex

{{{since "1.0.0"}}}

Sets a key with the provided value, and an expiration delay expressed in milliseconds. If the key does not exist, it is created beforehand.

[[_Redis documentation_]](https://redis.io/commands/psetex)


## Argument

* `_id`: key identifier


## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "psetex",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
