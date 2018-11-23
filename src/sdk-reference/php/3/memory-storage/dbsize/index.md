---
layout: sdk.html.hbs
algolia: true
title: dbsize
description: MemoryStorage:dbsize
algolia: true
---
  

# dbsize
Returns the number of keys in the application database.

[[_Redis documentation_]](https://redis.io/commands/dbsize)

---

## dbsize([options], callback)

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

Returns an integer containing the number of keys in the application database.

## Usage

[snippet=dbsize-1]
> Callback response:

```json
12
```