---
layout: sdk.html.hbs
algolia: true
title: hsetnx
description: MemoryStorage:hsetnx
---
  

# hsetnx
[snippet=hsetnx-1]
> Callback response:
Sets a field and its value in a hash, only if the field does not already exist.

[[_Redis documentation_]](https://redis.io/commands/hsetnx)

---

## hsetnx(key, field, value, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `field` | string | Field name to insert or to update |
| `value` | string | Associated field value |
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
