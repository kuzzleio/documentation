---
layout: sdk.html.hbs
algolia: true
title: zrank
description: MemoryStorage:zrank
---

  

# zrank
Returns the position of an element in a sorted set, with scores in ascending order. The index returned is 0-based (the lowest score member has an index of 0).

[[_Redis documentation_]](https://redis.io/commands/zrank)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the member's position in the sorted set.

## Usage

[snippet=zrank-1]
> Callback response:

```json
0
```