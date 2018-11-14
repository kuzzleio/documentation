---
layout: sdk.html.hbs
algolia: true
title: time
description: MemoryStorage:time
---
  

# time
[snippet=time-1]

> Callback response:
Returns the current server time.

[[_Redis documentation_]](https://redis.io/commands/time)

---

## time([options], callback)

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

Returns an array containing the following two items, in this order:

* a timestamp in [Epoch time](https://en.wikipedia.org/wiki/Unix_time)
* the number of microseconds already elapsed in the current second
