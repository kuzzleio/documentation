---
layout: sdk.html.hbs
algolia: true
title: pfadd
description: MemoryStorage:pfadd
---
  

# pfadd
[snippet=pfadd-1]

> Callback response:
Adds elements to an [HyperLogLog](https://en.wikipedia.org/wiki/HyperLogLog) data structure.

[[_Redis documentation_]](https://redis.io/commands/pfadd)

---

## pfadd(key, elements, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `elements` | array | Elements to add to the HyperLogLog structure |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | ``true`` |
---

## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.
