---
layout: sdk.html.hbs
algolia: true
title: getrange
description: MemoryStorage:getrange
---
  

# getrange

[snippet=getrange-1]
> Callback response:

[snippet=getrange-2]

Returns a substring of a key's value (index starts at position `0`).

[[_Redis documentation_]](https://redis.io/commands/getrange)

---

## getrange(key, start, end, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `start` | int | Starting index |
| `end` | int | Ending index |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns a substring of the key's value.
