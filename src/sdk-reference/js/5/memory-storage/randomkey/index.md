---
layout: sdk.html.hbs
algolia: true
title: randomkey
description: MemoryStorage:randomkey
algolia: true
---
  

# randomkey
Returns a random key from the memory storage.

[[_Redis documentation_]](https://redis.io/commands/randomkey)

---

## randomkey([options], callback)

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

Returns one of the stored key names, at random.

## Usage

[snippet=randomkey-1]
> Callback response:

```json
"key2"
```