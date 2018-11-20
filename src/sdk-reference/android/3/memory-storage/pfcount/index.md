---
layout: sdk.html.hbs
algolia: true
title: pfcount
description: MemoryStorage:pfcount
---

  

# pfcount
Returns the probabilistic cardinality of a [HyperLogLog](https://en.wikipedia.org/wiki/HyperLogLog) data structure, or of the merged HyperLogLog structures if more than 1 is provided (see [pfadd]({{ site_base_path }}sdk-reference/memory-storage/pfadd)).

[[_Redis documentation_]](https://redis.io/commands/pfcount)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the aggregated probabilistic cardinality of HyperLogLog structures.

## Usage

[snippet=pfcount-1]
> Callback response:

```json
42
```