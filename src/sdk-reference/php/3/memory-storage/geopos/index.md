---
layout: sdk.html.hbs
title: geopos
description: MemoryStorage:geopos
---
  

# geopos
Returns the positions (longitude, latitude) of the provided key's members (see [geoadd]({{ site_base_path }}sdk-reference/php/3/memory-storage/geoadd)).  

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

## Usage

[snippet=geopos-1]
> Callback response:

```json
[ [13.361389, 38.115556], [15.087269, 37.502669]]
```