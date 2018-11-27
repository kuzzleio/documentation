---
layout: sdk.html.hbs
algolia: true
title: llen
description: MemoryStorage:llen
algolia: true
---
  

# llen
Counts the number of items in a list.

[[_Redis documentation_]](https://redis.io/commands/llen)

---

## llen(key, [options], callback)

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

Returns an integer containing the number of items of a list.

## Usage

[snippet=llen-1]
> Callback response:

```json
3
```