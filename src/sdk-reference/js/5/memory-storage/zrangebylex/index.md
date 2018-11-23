---
layout: sdk.html.hbs
algolia: true
title: zrangebylex
description: MemoryStorage:zrangebylex
algolia: true
---
  

# zrangebylex
Returns elements in a sorted set where all members have equal score, using lexicographical ordering. The `min` and `max` values are inclusive by default. To change this behavior, please check the full documentation.

[[_Redis documentation_]](https://redis.io/commands/zrangebylex)

---

## zrangebylex(key, min, max, [options], [callback])

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

[snippet=zrangebylex-1]
> Callback response:

```json
[
  "member1",
  "member2",
  "..."
]
```