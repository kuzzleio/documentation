---
layout: sdk.html.hbs
title: keys
description: MemoryStorage:keys
---
  

# keys
Returns all keys matching the provided pattern.

[[_Redis documentation_]](https://redis.io/commands/keys)

---

## keys(pattern, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `pattern` | string | Pattern used to filter the returned key names |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |

---

## Callback Response

Returns an array of key names matching the provided pattern.

## Usage

[snippet=keys-1]
> Callback response:

```json
[
  "foo",
  "foobar",
  "foofighters"
]
```