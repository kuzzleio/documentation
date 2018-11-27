---
layout: sdk.html.hbs
algolia: true
title: hvals
description: MemoryStorage:hvals
algolia: true
---
  

# hvals
Returns all values contained in a hash.

[[_Redis documentation_]](https://redis.io/commands/hvals)

---

## hvals(key, [options], callback)

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

Returns an array containing the values of a hash.

## Usage

[snippet=hvals-1]
> Callback response:

```json
[
  "field1's value",
  "field2's value",
  "..."
]
```