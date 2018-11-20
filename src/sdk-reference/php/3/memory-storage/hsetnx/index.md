---
layout: sdk.html.hbs
algolia: true
title: hsetnx
description: MemoryStorage:hsetnx
---

  

# hsetnx
Sets a field and its value in a hash, only if the field does not already exist.

[[_Redis documentation_]](https://redis.io/commands/hsetnx)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=hsetnx-1]
> Callback response:

```json
true
```