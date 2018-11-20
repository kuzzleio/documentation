---
layout: sdk.html.hbs
algolia: true
title: zremrangebyrank
description: MemoryStorage:zremrangebyrank
---

  

# zremrangebyrank
Removes members from a sorted set with their position in the set between `start` and `stop` (inclusive).

Positions are 0-based, meaning the first member of the set has a position of 0.

[[_Redis documentation_]](https://redis.io/commands/zremrangebyrank)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of removed members from the sorted set.

## Usage

[snippet=zremrangebyrank-1]
> Callback response:

```json
2
```