---
layout: sdk.html.hbs
algolia: true
title: msetnx
description: MemoryStorage:msetnx
---

  

# msetnx
Sets the provided keys to their respective values, only if they do not exist. If a key exists, then the whole operation is aborted and no key is set.

[[_Redis documentation_]](https://redis.io/commands/msetnx)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=msetnx-1]
> Callback response:

```json
true
```