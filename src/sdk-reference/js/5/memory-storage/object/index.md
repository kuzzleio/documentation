---
layout: sdk.html.hbs
algolia: true
title: object
description: MemoryStorage:object
---
  

# object

[snippet=object-1]
> Callback response:

[snippet=object-2]

Inspects the low-level properties of a key.

[[_Redis documentation_]](https://redis.io/commands/object)

---

## object(key, subcommand, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| `key` | string | Key identifier |
| `subcommand` | string | Name of the low-level property to inspect.<br/>Allowed values: `refcount`, `encoding`, `idletime` |
| `options` | JSON Object | Optional parameters |
| `callback` | function | Callback |
---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| `queuable` | boolean | Make this request queuable or not  | `true` |
---

## Callback Response

Returns the value of the inspected property.
