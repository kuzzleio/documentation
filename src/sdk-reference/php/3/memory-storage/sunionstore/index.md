---
layout: sdk.html.hbs
algolia: true
title: sunionstore
description: MemoryStorage:sunionstore
---

  

# sunionstore
Computes the union of the provided sets of unique values and stores the result in the `destination` key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/sunionstore)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the number of elements in the stored union.

## Usage

[snippet=sunionstore-1]
> Callback response:

```json
4
```