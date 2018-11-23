---
layout: sdk.html.hbs
algolia: true
title: bitpos
description: MemoryStorage:bitpos
algolia: true
---
  

# bitpos
Returns the position of the first bit set to 1 or 0 in a string, or in a substring.

[[_Redis documentation_]](https://redis.io/commands/bitpos)

---

## bitpos(key, bit, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `bit` | int | Bit to search.<br/>Allowed values: `0`, `1` |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `end` | int | Ending offset | `-1` |
| `queuable` | boolean | Make this request queuable or not  | `true` |
| `start` | int | Starting offset | `0` |

---

## Callback Response

Returns an integer containing the first position of the searched bit in the string value.

## Usage

[snippet=bitpos-1]
> Callback response:

```json
0
```