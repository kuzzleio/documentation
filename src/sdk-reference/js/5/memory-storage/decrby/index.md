---
layout: sdk.html.hbs
algolia: true
title: decrby
description: MemoryStorage:decrby
---

  

# decrby
Decrements the number stored at `key` by a provided integer value. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/decrby)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated key value.

## Usage

[snippet=decrby-1]
> Callback response:

```json
57
```