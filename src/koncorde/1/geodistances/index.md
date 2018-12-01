---
layout: full.html.hbs
algolia: true
title: Geodistances
description: Geodistance formats description
order: 400
---

## Geodistances

Distances used in geofencing filters such as [geoDistance]({{ site_base_path }}kuzzle-dsl/terms/geo-distance/) or [geoDistanceRange]({{ site_base_path }}kuzzle-dsl/terms/geo-distance-range/) can be expressed in various ways.

Accepted units:

* `m`, `meter`, `meters`
* `ft`, `feet`, `feets`
* `in`, `inch`, `inches`
* `yd`, `yard`, `yards`
* `mi`, `mile`, `miles`

**Note:** if no unit is specified, then Koncorde will express the geodistance in meters.

Accepted unit modifiers: from `yocto-` (10e-21) to `yotta-` (10e24), and their corresponding short forms (e.g. `kilometers` or `km`)

Accepted formats: `<int (spaces accepted)>[.|,]<decimals><spaces><unit>`.  

### Example

The following distances are all equivalent and valid input parameters:

```
1000
1000 m
1km
3280.839895013123 ft
3280.839895013123FT
39370.078740157485 inches
39370.078740157485 inch
39370.078740157485 in
1 093,6132983377079 yd
0.6213727366498067 miles
```
