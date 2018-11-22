---
layout: sdk.html.hbs
algolia: true
title: zcard
description: MemoryStorage:zcard
algolia: true
---
  

# zcard
Returns the number of elements held by a sorted set.

[[_Redis documentation_]](https://redis.io/commands/zcard)

---

## zcard(key, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns an integer containing the number of elements in a sorted set.

## Usage

[snippet=zcard-1]
> Callback response:

```json
4
```