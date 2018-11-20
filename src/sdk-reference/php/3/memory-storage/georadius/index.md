---
layout: sdk.html.hbs
algolia: true
title: georadius
description: MemoryStorage:georadius
---

  

# georadius
> Callback response, with no option provided:

```json
[
  {"name": "Palermo"},
  {"name": "Catania"}
]
```

> Callback response, with the "withcoord" option:

```json
[
  {"name": "Palermo", "coordinates": [13.361389338970184, 38.1155563954963]},
  {"name": "Catania", "coordinates": [15.087267458438873, 37.50266842333162]}
]
```

> Callback response, with the "withdist" option:

```json
[
  {"name": "Palermo", "distance": 190.4424},
  {"name": "Catania", "distance": 56.4413}
]
```

Returns the members (added with [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)) of a given key inside the provided geospatial radius.

[[_Redis documentation_]](https://redis.io/commands/georadius)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of names for points that are inside the provided radius.

## Usage

[snippet=georadius-1]
