---
layout: sdk.html.hbs
algolia: true
title: zrangebylex
description: MemoryStorage:zrangebylex
---

  

# zrangebylex
Returns elements in a sorted set where all members have equal score, using lexicographical ordering. The `min` and `max` values are inclusive by default. To change this behavior, please check the full documentation.

[[_Redis documentation_]](https://redis.io/commands/zrangebylex)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of matching members.

## Usage

[snippet=zrangebylex-1]
> Callback response:

```json
[
  "member1",
  "member2",
  "..."
]
```