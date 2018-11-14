---
layout: sdk.html.hbs
algolia: true
title: geodist
description: MemoryStorage:geodist
---
  

# geodist
[snippet=geodist-1]

> Callback response:
Returns the distance between two geospatial members of a key (see [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)).  
The returned distance is expressed in meters by default.

[[_Redis documentation_]](https://redis.io/commands/geodist)

---

## geodist(key, member1, member2, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `member1` | string | Name of the first geospatial point |
| `member2` | string | Name of the second geospatial point |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
| `unit` | string | Distance unit.<br/>Allowed values: `m`, `km`, `mi`, `ft` | `m` |

---

## Callback Response

Returns the calculated distance between the two provided geospatial points.
