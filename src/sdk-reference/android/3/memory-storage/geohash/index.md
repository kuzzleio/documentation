---
layout: sdk.html.hbs
algolia: true
title: geohash
description: MemoryStorage:geohash
---

  

# geohash
Returns a valid [geohash](https://en.wikipedia.org/wiki/Geohash) for the provided key's members (see [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)).  

[[_Redis documentation_]](https://redis.io/commands/geohash)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of geohashes, in the same order than the provided members list.

## Usage

[snippet=geohash-1]
> Callback response:

```json
["sqc8b49rny0", "sqdtr74hyu0"]
```