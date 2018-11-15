---
layout: sdk.html.hbs
algolia: true
title: rpop
description: MemoryStorage:rpop
---
  

# rpop
Removes and returns the last element of a list.

[[_Redis documentation_]](https://redis.io/commands/rpop)

---

## rpop(key, [options], [callback])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | ``true`` |
---

## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns the value of the removed item.

## Usage

[snippet=rpop-1]
> Callback response:

```json
"foo"
```