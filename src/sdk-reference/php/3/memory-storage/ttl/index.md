---
layout: sdk.html.hbs
algolia: true
title: ttl
description: MemoryStorage:ttl
---
  

# ttl
[snippet=ttl-1]

> Callback response:
Returns the remaining time to live of a key, in seconds, or a negative value if the key does not exist or if it is persistent.

[[_Redis documentation_]](https://redis.io/commands/ttl)

---

## ttl(key, [options], callback)

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

Returns an integer containing the remaining time to live of the key, in seconds.
