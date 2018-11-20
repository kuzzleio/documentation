---
layout: sdk.html.hbs
algolia: true
title: zrevrange
description: MemoryStorage:zrevrange
---

  

# zrevrange
Identical to [zrange]({{ site_base_path }}sdk-reference/memory-storage/zrange), except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrange)


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

[snippet=zrevrange-1]
> Callback response:

```json
[
  { "member": "baz", "score": 3 },
  { "member": "bar", "score": 2 },
  { "member": "foo", "score": 1 }
]
```