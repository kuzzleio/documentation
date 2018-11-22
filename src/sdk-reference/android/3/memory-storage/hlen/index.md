---
layout: sdk.html.hbs
algolia: true
title: hlen
description: MemoryStorage:hlen
algolia: true
---
  

# hlen
Returns the number of fields contained in a hash.

[[_Redis documentation_]](https://redis.io/commands/hlen)

---

## hlen(key, [options], callback)

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

Returns an integer containing the number of fields in the hash.

## Usage

[snippet=hlen-1]
> Callback response:

```json
13
```