---
layout: sdk.html.hbs
algolia: true
title: incrbyfloat
description: MemoryStorage:incrbyfloat
---

  

# incrbyfloat
Increments the number stored at `key` by the provided float value. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/incrbyfloat)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a floating point number that contains the updated key value.

## Usage

[snippet=incrbyfloat-1]
> Callback response:

```json
38.85841
```