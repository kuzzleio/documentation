---
layout: sdk.html.hbs
algolia: true
title: geopos
description: MemoryStorage:geopos
---
  

# geopos

[snippet=geopos-1]
> Callback response:

[snippet=geopos-2]
Returns the positions (longitude, latitude) of the provided key's members (see [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)).  

[[_Redis documentation_]](https://redis.io/commands/geopos)

---

## geopos(key, members, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `members` | array | List of geospatial points contained in the key |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns an array of longitude-latitude pairs, in the same order as the provided members list.  
