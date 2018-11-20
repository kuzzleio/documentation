---
layout: sdk.html.hbs
algolia: true
title: bitop
description: MemoryStorage:bitop
---

  

# bitop
Performs a bitwise operation between multiple keys (containing string values) and stores the result in the destination key.

[[_Redis documentation_]](https://redis.io/commands/bitop)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the length of the new key's value.

## Usage

[snippet=bitop-1]
> Callback response:

```json
42
```