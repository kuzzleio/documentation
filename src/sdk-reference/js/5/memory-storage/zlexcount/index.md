---
layout: sdk.html.hbs
algolia: true
title: zlexcount
description: MemoryStorage:zlexcount
---

  

# zlexcount
Counts elements in a sorted set where all members have equal score, using lexicographical ordering. The `min` and `max` values are inclusive by default. To change this behavior, please check the syntax detailed in the [Redis documentation](https://redis.io/commands/zrangebylex).

[[_Redis documentation_]](https://redis.io/commands/zlexcount)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the number of elements in the provided lexicographical value range.

## Usage

[snippet=zlexcount-1]
> Callback response:

```json
2
```