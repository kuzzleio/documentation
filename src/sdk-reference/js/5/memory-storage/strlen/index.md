---
layout: sdk.html.hbs
algolia: true
title: strlen
description: MemoryStorage:strlen
---
  

# strlen

[snippet=strlen-1]
> Callback response:

[snippet=strlen-2]

Returns the length of a value stored at `key`.

[[_Redis documentation_]](https://redis.io/commands/strlen)

---

## strlen(key, [options], callback)

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

Returns an integer containing the length of a value.
