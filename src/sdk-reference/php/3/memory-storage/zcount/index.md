---
layout: sdk.html.hbs
algolia: true
title: zcount
description: MemoryStorage:zcount
---

  

# zcount
Returns the number of elements held by a sorted set with a score between the provided `min` and `max` values.

By default, the provided min and max values are inclusive. This behavior can be changed using the syntax described in the Redis [ZRANGEBYSCORE](https://redis.io/commands/zrangebyscore) documentation.

[[_Redis documentation_]](https://redis.io/commands/zcount)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the number of elements in the provided score range.

## Usage

[snippet=zcount-1]
> Callback response:

```json
2
```