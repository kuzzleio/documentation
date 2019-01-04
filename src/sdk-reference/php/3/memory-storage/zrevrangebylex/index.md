---
layout: sdk.html.hbs
title: zrevrangebylex
description: MemoryStorage:zrevrangebylex
---
  

# zrevrangebylex
Identical to [zrangebylex]({{ site_base_path }}sdk-reference/php/3/memory-storage/zrangebylex) except that the sorted set is traversed in descending order.

[[_Redis documentation_]](https://redis.io/commands/zrevrangebylex)

---

## zrevrangebylex(key, min, max, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `min` | string | Minimum member value (inclusive by default) |
| `max` | string | Maximum member value (inclusive by default) |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `limit` | array | Limit the result set to a range of matching elements (similar to _SELECT LIMIT offset, count_ in SQL).<br/>Format: `[<offset(int)>, <count(int)>]` | `null` |
| `queuable` | boolean | Make this request queuable or not  | ``true`` |
---

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