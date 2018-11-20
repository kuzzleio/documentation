---
layout: full.html.hbs
algolia: true
title: geohash
---


# geohash

{{{since "1.0.0"}}}

Converts a key's geopoints (see [geoadd]({{ site_base_path }}api/1/controller-memory-storage/geoadd/)) into [geohashes](https://en.wikipedia.org/wiki/Geohash).

[[_Redis documentation_]](https://redis.io/commands/geohash)


## Arguments

* `_id`: key containing the geopoints to convert
* `members`: list of geopoint names to convert

---

## Response

Returns the converted geohashes, in the same order than the one provided in the query.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "geohash",
  "collection": null,
  "index": null,
  "result": ["geohash1", "geohash2", "..."]
}
```
