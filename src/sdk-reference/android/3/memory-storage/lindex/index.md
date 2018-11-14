---
layout: sdk.html.hbs
algolia: true
title: lindex
description: MemoryStorage:lindex
---
  

# lindex
[snippet=lindex-1]
> Callback response:
Returns the element at the provided index in a list.

[[_Redis documentation_]](https://redis.io/commands/lindex)

---

## lindex(key, index, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `index` | int | Element position in the list |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns a string containing the retrieved element's value.
