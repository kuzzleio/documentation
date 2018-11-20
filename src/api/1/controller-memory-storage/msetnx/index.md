---
layout: full.html.hbs
algolia: true
title: msetnx
---


# msetnx

{{{since "1.0.0"}}}

Sets the provided keys to their respective values, only if they do not exist. If a key exists, then the whole operation is aborted and no key is set.

[[_Redis documentation_]](https://redis.io/commands/msetnx)


## Body properties

* `entries`: an array of objects. Each object describes a new key-value pair to set, using the following properties:
  * `key`: key identifier
  * `value`: new value

---

## Response

Returns either `0` (command failed), or `1` (command succeeded).

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "msetnx",
  "collection": null,
  "index": null,
  "result": 1
}
```
