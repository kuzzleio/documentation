---
layout: sdk.html.hbs
algolia: true
title: smove
description: MemoryStorage:smove
---
  

# smove
[snippet=smove-1]

> Callback response:
Moves a member from a set of unique values to another.

[[_Redis documentation_]](https://redis.io/commands/smove)

---

## smove(key, destination, member, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Source key identifier |
| `destination` | string | Destination key identifier |
| `member` | string | Member to be moved |
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
