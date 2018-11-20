---
layout: sdk.html.hbs
algolia: true
title: zrevrangebyscore
description: MemoryStorage:zrevrangebyscore
---

  

# zrevrangebyscore
Identical to [zrangebyscore]({{ site_base_path }}sdk-reference/memory-storage/zrangebyscore) except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrangebyscore)


## Options

| Option | Type | Description | Default |
|
## Return value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of matching members.

## Usage

[snippet=zrevrangebyscore-1]
> Callback response:

```json
[
  { "member": "baz", "score": 3 },
  { "member": "bar", "score": 2 },
  { "member": "foo", "score": 1 }
]
```