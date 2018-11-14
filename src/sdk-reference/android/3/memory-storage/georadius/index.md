---
layout: sdk.html.hbs
algolia: true
title: georadius
description: MemoryStorage:georadius
---
  

# georadius
[snippet=georadius-1]
> Callback response, with no option provided:
> Callback response, with the "withcoord" option:
> Callback response, with the "withdist" option:
Returns the members (added with [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)) of a given key inside the provided geospatial radius.

[[_Redis documentation_]](https://redis.io/commands/georadius)

---

## georadius(key, longitude, latitude, distance, unit, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `longitude` | double | Longitude of the center of the radius |
| `latitude` | double | Latitude of the center of the radius |
| `distance` | double | Maximum distance from the center |
| `unit`  | string | Distance unit |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `count` | int | Limit the result set to `count` members | `null` |
| `queuable` | boolean | Make this request queuable or not  | `true` |
| `sort` | string | Return items from the nearest to the farthest to the center (`ASC`) or vice versa (`DESC`) | `null` |
| `withcoord` | boolean | Also return the longitude and latitude coordinates of the matching items | `false` |
| `withdist` | boolean | Also return the distance of the returned items from the specified center, in the same unit than the one provided with `unit` | `false` |

---

## Callback Response

Returns an array of names for points that are inside the provided radius.
