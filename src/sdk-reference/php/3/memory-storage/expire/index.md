---
layout: sdk.html.hbs
algolia: true
title: expire
description: MemoryStorage:expire
---
  

# expire
[snippet=expire-1]

> Callback response:
Sets a timeout (in seconds) on a key. After the timeout has expired, the key will automatically be deleted.

[[_Redis documentation_]](https://redis.io/commands/expire)

---

## expire(key, seconds, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `seconds` | int | Time to live, in seconds |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | ``true`` |

---

## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.
