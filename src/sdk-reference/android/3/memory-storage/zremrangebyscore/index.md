---
layout: sdk.html.hbs
algolia: true
title: zremrangebyscore
description: MemoryStorage:zremrangebyscore
---

  

# zremrangebyscore
Removes members from a sorted set with a score between `min` and `max` (inclusive by default).

[[_Redis documentation_]](https://redis.io/commands/zremrangebyscore)


## Options

| Option | Type | Description | Default |
|
## Return value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of removed members from the sorted set.

## Usage

[snippet=zremrangebyscore-1]
> Callback response:

```json
2
```