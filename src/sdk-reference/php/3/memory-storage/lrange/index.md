---
layout: sdk.html.hbs
algolia: true
title: lrange
description: MemoryStorage:lrange
---
  

# lrange
[snippet=lrange-1]

> Callback response:
Returns the list elements between the start and stop positions (inclusive).

[[_Redis documentation_]](https://redis.io/commands/lrange)

---

## lrange(key, start, stop, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `start` | int | Start position |
| `stop` | int | End position |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns an array of retrieved values.
