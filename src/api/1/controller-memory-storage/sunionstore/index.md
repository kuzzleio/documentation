---
layout: full.html.hbs
algolia: true
title: sunionstore
---


# sunionstore

{{{since "1.0.0"}}}

Computes the union of multiple sets of unique values and stores it in a new set.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sunionstore)


## Body properties

* `destination`: destination for the union result
* `keys`: array of set identifiers

---

## Response

Returns the number of members stored in the destination set.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sunionstore",
  "collection": null,
  "index": null,
  "result": 31
}
```
