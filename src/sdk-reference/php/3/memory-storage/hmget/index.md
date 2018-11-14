---
layout: sdk.html.hbs
algolia: true
title: hmget
description: MemoryStorage:hmget
---
  

# hmget
[snippet=hmget-1]

> Callback response:
Returns the values of the specified hash’s fields.

[[_Redis documentation_]](https://redis.io/commands/hmget)

---

## hmget(key, fields, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `fields` | array | List of fields to examine |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns an array containing the specified fields values.
