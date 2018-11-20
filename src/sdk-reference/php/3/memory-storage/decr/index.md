---
layout: sdk.html.hbs
algolia: true
title: decr
description: MemoryStorage:decr
---

  

# decr
Decrements the number stored at `key` by 1. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/decr)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated key value.

## Usage

[snippet=decr-1]
> Callback response:

```json
-1
```