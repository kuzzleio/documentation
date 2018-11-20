---
layout: full.html.hbs
algolia: true
title: sdiff
---


# sdiff

{{{since "1.0.0"}}}

Returns the difference between a reference set and a list of other sets.

[[_Redis documentation_]](https://redis.io/commands/sdiff)


## Argument

* `_id`: reference set key identifier
* `keys`: list of sets to compare to the reference set

---

## Response

Returns an array of differences.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "sdiff",
  "collection": null,
  "index": null,
  "result": [
    "diff value1",
    "diff value2",
    "..."
  ]
}
```
