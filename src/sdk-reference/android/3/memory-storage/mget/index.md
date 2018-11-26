---
layout: sdk.html.hbs
algolia: true
title: mget
description: MemoryStorage:mget
algolia: true
---
  

# mget
Returns the values of the provided keys.

[[_Redis documentation_]](https://redis.io/commands/mget)

---

## mget(keys, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `keys` | string | List of keys to retrieve |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns an array of the specified keys' values.

## Usage

[snippet=mget-1]
> Callback response:

```json
[
  "key1's value",
  "key2's value",
  "..."
]
```