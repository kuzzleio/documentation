---
layout: full.html.hbs
algolia: true
title: geodist
---


# geodist

{{{since "1.0.0"}}}

Returns the distance between two geospatial members of a key (see [geoadd]({{ site_base_path }}api/1/controller-memory-storage/geoadd/)).

The returned distance is expressed in meters by default.

[[_Redis documentation_]](https://redis.io/commands/geodist)


## Arguments

* `_id`: key containing the geopoints to compare
* `member1`: first geopoint name
* `member2`: second geopoint name

### Optional:

* `unit`: the unit used for the returned calculated distance. Accepted values: `m`, `km`, `mi`, `ft`

---

## Response

Returns the calculated distance.

```javascript
{
  "requestId": "<unique request identifier>",
  "status": 200,
  "error": null,
  "controller": "ms",
  "action": "geodist",
  "collection": null,
  "index": null,
  "result": 192.3
}
```
