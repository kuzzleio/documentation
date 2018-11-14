---
layout: sdk.html.hbs
algolia: true
title: keys
description: MemoryStorage:keys
---
  

# keys
[snippet=keys-1]

> Callback response:
Returns all keys matching the provided pattern.

[[_Redis documentation_]](https://redis.io/commands/keys)

---

## keys(pattern, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `pattern` | string | Pattern used to filter the returned key names |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns an array of key names matching the provided pattern.
