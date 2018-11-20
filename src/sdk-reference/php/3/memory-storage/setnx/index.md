---
layout: sdk.html.hbs
algolia: true
title: setnx
description: MemoryStorage:setnx
---

  

# setnx
Sets a value on a key, only if it does not already exist.

[[_Redis documentation_]](https://redis.io/commands/setnx)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=setnx-1]
> Callback response:

```json
true
```