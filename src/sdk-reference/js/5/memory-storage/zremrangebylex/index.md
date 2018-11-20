---
layout: sdk.html.hbs
algolia: true
title: zremrangebylex
description: MemoryStorage:zremrangebylex
---

  

# zremrangebylex
Removes members from a sorted set where all elements have the same score, using lexicographical ordering. The `min` and `max` interval are inclusive, see the [Redis documentation](https://redis.io/commands/zrangebylex) to change this behavior.

[[_Redis documentation_]](https://redis.io/commands/zremrangebylex)


## Options

| Option | Type | Description | Default |
|
## Return value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of removed members from the sorted set.

## Usage

[snippet=zremrangebylex-1]
> Callback response:

```json
2
```