---
layout: sdk.html.hbs
algolia: true
title: geopos
description: MemoryStorage:geopos
---

  

# geopos
Returns the positions (longitude, latitude) of the provided key's members (see [geoadd]({{ site_base_path }}sdk-reference/memory-storage/geoadd)).  

[[_Redis documentation_]](https://redis.io/commands/geopos)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of longitude-latitude pairs, in the same order as the provided members list.  

## Usage

[snippet=geopos-1]
> Callback response:

```json
[ [13.361389, 38.115556], [15.087269, 37.502669]]
```