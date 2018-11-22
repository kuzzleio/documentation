---
layout: sdk.html.hbs
algolia: true
title: hstrlen
description: MemoryStorage:hstrlen
algolia: true
---
  

# hstrlen
Returns the string length of a field’s value in a hash.

[[_Redis documentation_]](https://redis.io/commands/hstrlen)

---

## hstrlen(key, field, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `field` | string | Hash field name |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns the string length of a field's value.

## Usage

[snippet=hstrlen-1]
> Callback response:

```json
3
```