---
layout: sdk.html.hbs
algolia: true
title: hincrby
description: MemoryStorage:hincrby
---

  

# hincrby
Increments the number stored in a hash field by the provided integer value.

[[_Redis documentation_]](https://redis.io/commands/hincrby)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns the newly incremented value.

## Usage

[snippet=hincrby-1]
> Callback response:

```json
45
```