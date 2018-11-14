---
layout: sdk.html.hbs
algolia: true
title: hincrbyfloat
description: MemoryStorage:hincrbyfloat
---
  

# hincrbyfloat
[snippet=hincrbyfloat-1]
> Callback response:
Increments the number stored in a hash field by the provided float value.

[[_Redis documentation_]](https://redis.io/commands/hincrbyfloat)

---

## hincrbyfloat(key, field, value, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `field` | string | Hash field to increment |
| `value` | double | Increment value |
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

Returns the newly incremented value, as a floating point number.
