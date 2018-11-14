---
layout: sdk.html.hbs
algolia: true
title: ping
description: MemoryStorage:ping
---
  

# ping
[snippet=ping-1]
> Callback response:
Pings the memory storage database.

[[_Redis documentation_]](https://redis.io/commands/ping)

---

## ping([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns a simple "PONG" string.
