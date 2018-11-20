---
layout: sdk.html.hbs
algolia: true
title: zrem
description: MemoryStorage:zrem
---

  

# zrem
Removes members from a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zrem)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of members removed from the sorted set.

## Usage

[snippet=zrem-1]
> Callback response:

```json
3
```