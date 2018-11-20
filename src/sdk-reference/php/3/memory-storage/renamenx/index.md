---
layout: sdk.html.hbs
algolia: true
title: renamenx
description: MemoryStorage:renamenx
---

  

# renamenx
Renames a key to `newkey`, only if `newkey` does not already exist.

[[_Redis documentation_]](https://redis.io/commands/renamenx)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=renamenx-1]
> Callback response:

```json
true
```