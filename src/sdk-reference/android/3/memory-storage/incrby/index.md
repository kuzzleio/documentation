---
layout: sdk.html.hbs
algolia: true
title: incrby
description: MemoryStorage:incrby
---

  

# incrby
Increments the number stored at `key` by the provided integer value. If the key does not exist, it is set to 0 before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/incrby)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated key value.

## Usage

[snippet=incrby-1]
> Callback response:

```json
39
```