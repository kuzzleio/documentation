---
layout: sdk.html.hbs
algolia: true
title: type
description: MemoryStorage:type
---
  

# type

[snippet=type-1]
> Callback response:

[snippet=type-2]

Returns the type of the value held by a key.

[[_Redis documentation_]](https://redis.io/commands/type)

---

## type(key, [options], callback)

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

Returns one of the following values: `hash`, `list`, `set`, `string`, `zset`.
