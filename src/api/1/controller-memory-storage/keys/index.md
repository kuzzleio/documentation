---
layout: full.html.hbs
algolia: true
title: keys
---



# keys

{{{since "1.0.0"}}}

Returns all keys matching the provided pattern.

[[_Redis documentation_]](https://redis.io/commands/keys)


## Arguments

* `_id`: key identifier
* `pattern`: match pattern

---

## Response

Returns the list of matching keys.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "keys",
  "collection": null,
  "index": null,
  "result": [
    "fookey1",
    "fookey2",
    "foo..."
  ]
}
```
