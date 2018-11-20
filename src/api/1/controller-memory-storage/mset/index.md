---
layout: full.html.hbs
algolia: true
title: mset
---


# mset

{{{since "1.0.0"}}}

Sets the provided keys to their respective values. If a key does not exist, it is created. Otherwise, the key's value is overwritten.

[[_Redis documentation_]](https://redis.io/commands/mset)


## Body properties

* `entries`: an array of objects. Each object describes a new key-value pair to set, using the following properties:
  * `key`: key identifier
  * `value`: new value

---

## Response

Returns an acknowledgement.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "mset",
  "collection": null,
  "index": null,
  "result": "OK"
}
```
