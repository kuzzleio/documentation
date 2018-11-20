---
layout: sdk.html.hbs
algolia: true
title: georadiusbymember
description: MemoryStorage:georadiusbymember
---

  

# georadiusbymember
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

Returns the members (added with [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)) of a given key inside the provided geospatial radius, centered around one of a key's member.
[[_Redis documentation_]](https://redis.io/commands/georadiusbymember)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of names for points that are inside the provided radius.

## Usage

[snippet=georadiusbymember-1]
> Callback response:

```json
[
  {"name": "Palermo"},
  {"name": "Catania"}
]
```