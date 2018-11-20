---
layout: sdk.html.hbs
algolia: true
title: sinterstore
description: MemoryStorage:sinterstore
---

  

# sinterstore
Computes the intersection of the provided sets of unique values and stores the result in the `destination` key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sinterstore)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of elements in the stored intersection.

## Usage

[snippet=sinterstore-1]
> Callback response:

```json
4
```