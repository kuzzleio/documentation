---
layout: sdk.html.hbs
algolia: true
title: hexists
description: MemoryStorage:hexists
---
  

# hexists

[snippet=hexists-1]
> Callback response:

[snippet=hexists-2]

Checks if a field exists in a hash.

[[_Redis documentation_]](https://redis.io/commands/hexists)

---

## hexists(key, field, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `field` | string | Field name |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

A boolean value specifying if the field exists or not.
