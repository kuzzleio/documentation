---
layout: full.html.hbs
algolia: true
title: bitpos
---


# bitpos

{{{since "1.0.0"}}}

Returns the position of the first bit set to 1 or 0 in a string, or in a substring.

[[_Redis documentation_]](https://redis.io/commands/bitpos)


## Arguments

* `_id`: key to examine
* `bit`: bit to look for. Accepted values: `0`, `1`

### Optional:

* `start`: search starts at the provided offset
* `end`: search ends at the provided offset

---

## Response

Returns the position of the first bit found matching the searched value.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "bitpos",
  "collection": null,
  "index": null,
  "result": 42
}
```
