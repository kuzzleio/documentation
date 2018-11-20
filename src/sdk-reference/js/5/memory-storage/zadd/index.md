---
layout: sdk.html.hbs
algolia: true
title: zadd
description: MemoryStorage:zadd
---

  

# zadd
Adds the specified elements to the sorted set stored at `key`. If the key does not exist, it is created, holding an empty sorted set. If it already exists and does not hold a sorted set, an error is returned.

Scores are expressed as floating point numbers.

If a member to insert is already in the sorted set, its score is updated and the member is reinserted at the right position in the set.

[[_Redis documentation_]](https://redis.io/commands/zadd)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of elements added to the sorted set.

## Usage

[snippet=zadd-1]
> Callback response:

```json
3
```