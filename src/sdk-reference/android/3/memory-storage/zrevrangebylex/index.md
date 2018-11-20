---
layout: sdk.html.hbs
algolia: true
title: zrevrangebylex
description: MemoryStorage:zrevrangebylex
---

  

# zrevrangebylex
Identical to [zrangebylex]({{ site_base_path }}sdk-reference/memory-storage/zrangebylex) except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrangebylex)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of matching members.

## Usage

[snippet=zrevrangebylex-1]
> Callback response:

```json
[
  "member1",
  "member2",
  "..."
]
```