---
layout: sdk.html.hbs
algolia: true
title: sdiffstore
description: MemoryStorage:sdiffstore
---

  

# sdiffstore
Computes the difference between the set of unique values stored at `key` and the other provided sets, and stores the result in the key stored at `destination`.

If the `destination` key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sdiffstore)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of stored elements.

## Usage

[snippet=sdiffstore-1]
> Callback response:

```json
3
```