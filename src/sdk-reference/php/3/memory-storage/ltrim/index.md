---
layout: sdk.html.hbs
algolia: true
title: ltrim
description: MemoryStorage:ltrim
---
  

# ltrim
[snippet=ltrim-1]

Trims an existing list so that it will contain only the specified range of elements specified.

[[_Redis documentation_]](https://redis.io/commands/ltrim)

---

## ltrim(key, start, stop, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `start` | int | Starting position of the range of items to keep (inclusive) |
| `stop` | int | Ending position of the range of items to keep (inclusive) |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | ``true`` |
---

## Return value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns null if successful.
