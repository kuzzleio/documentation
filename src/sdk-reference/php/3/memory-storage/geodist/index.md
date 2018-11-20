---
layout: sdk.html.hbs
algolia: true
title: geodist
description: MemoryStorage:geodist
---

  

# geodist
Returns the distance between two geospatial members of a key (see [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)).  
The returned distance is expressed in meters by default.

[[_Redis documentation_]](https://redis.io/commands/geodist)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns the calculated distance between the two provided geospatial points.

## Usage

[snippet=geodist-1]
> Callback response:

```json
166274.1516
```