---
layout: sdk.html.hbs
algolia: true
title: sunion
description: MemoryStorage:sunion
algolia: true
---
  

# sunion
Returns the union of the provided sets of unique values.

[[_Redis documentation_]](https://redis.io/commands/sunion)

---

## sunion(keys, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `keys` | string | List of sets of unique values |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns an array of values in the computed union.

## Usage

[snippet=sunion-1]
> Callback response:

```json
[
  "union value1",
  "union value2",
  "..."
]
```