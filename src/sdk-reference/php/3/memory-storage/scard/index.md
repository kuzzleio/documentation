---
layout: sdk.html.hbs
algolia: true
title: scard
description: MemoryStorage:scard
---
  

# scard
[snippet=scard-1]

> Callback response:
Returns the number of members stored in a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/scard)

---

## scard(key, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns an integer containing the number of items in the set.
