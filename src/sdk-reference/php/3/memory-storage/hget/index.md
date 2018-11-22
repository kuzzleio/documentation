---
layout: sdk.html.hbs
algolia: true
title: hget
description: MemoryStorage:hget
algolia: true
---
  

# hget
Returns the field’s value of a hash.

[[_Redis documentation_]](https://redis.io/commands/hget)

---

## hget(key, field, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `field` | string | Field name |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns the requested field's value.

## Usage

[snippet=hget-1]
> Callback response:

```json
"foo"
```