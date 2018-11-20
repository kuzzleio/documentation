---
layout: full.html.hbs
algolia: true
title: pfcount
---


# pfcount

{{{since "1.0.0"}}}

Returns the probabilistic cardinality of a [HyperLogLog](https://en.wikipedia.org/wiki/HyperLogLog) data structure, or of the merged HyperLogLog structures if more than 1 is provided (see [pfadd]({{ site_base_path }}api/1/controller-memory-storage/pfadd)).

[[_Redis documentation_]](https://redis.io/commands/pfcount)


## Argument

* `keys`: hyperloglog key identifiers

---

## Response

Returns the probabilistic cardinality.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "pfcount",
  "collection": null,
  "index": null,
  "result": 6
}
```
