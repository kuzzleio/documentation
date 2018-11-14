---
layout: sdk.html.hbs
algolia: true
title: georadiusbymember
description: MemoryStorage:georadiusbymember
---
  

# georadiusbymember

[snippet=georadiusbymember-1]
> Callback response:

[snippet=georadiusbymember-2]

> Callback response, with the "withcoord" option:

[snippet=georadiusbymember-3]

> Callback response, with the "withdist" option:

[snippet=georadiusbymember-4]

Returns the members (added with [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)) of a given key inside the provided geospatial radius, centered around one of a key's member.
[[_Redis documentation_]](https://redis.io/commands/georadiusbymember)

---

## georadiusbymember(key, member, distance, unit, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `member` | string | Name of the point to use as the center of the radius |
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
