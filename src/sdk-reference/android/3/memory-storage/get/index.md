---
layout: sdk.html.hbs
algolia: true
title: get
description: MemoryStorage:get
---
  

# get
[snippet=get-1]
> Callback response:
Returns the value of a key, or null if the key doesn’t exist.

[[_Redis documentation_]](https://redis.io/commands/get)

---

## get(key, [options], callback)

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

Returns the key's value.
