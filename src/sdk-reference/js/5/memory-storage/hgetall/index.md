---
layout: sdk.html.hbs
algolia: true
title: hgetall
description: MemoryStorage:hgetall
---
  

# hgetall

[snippet=hgetall-1]
> Callback response:

[snippet=hgetall-2]

Returns all fields and values of a hash.

[[_Redis documentation_]](https://redis.io/commands/hgetall)

---

## hgetall(key, [options], callback)

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

Returns a JSON object containing the fields and values of a hash.
