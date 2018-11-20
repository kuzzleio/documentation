---
layout: sdk.html.hbs
algolia: true
title: zrange
description: MemoryStorage:zrange
---

  

# zrange
Returns elements from a sorted set depending on their position in the set, from a `start` position index to a `stop` position index (inclusives).  
First position starts at `0`.

[[_Redis documentation_]](https://redis.io/commands/zrange)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of objects, each containing the following properties:

* `member`: member value in the sorted set
* `score`: member associated score

## Usage

[snippet=zrange-1]
> Callback response:

```json
[
  { "member": "foo", "score": 1 },
  { "member": "bar", "score": 2 },
  { "member": "baz", "score": 3 }
]
```