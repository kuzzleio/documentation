---
layout: full.html.hbs
algolia: true
title: smembers
---


# smembers

{{{since "1.0.0"}}}

Returns the members of a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/smembers)



## Argument

* `_id`: set key identifier

---

## Response

Returns the list of the set's members.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "smembers",
  "collection": null,
  "index": null,
  "result": [
    "member1",
    "member2",
    "..."
  ]
}
```
