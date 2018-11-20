---
layout: sdk.html.hbs
algolia: true
title: zinterstore
description: MemoryStorage:zinterstore
---

  

# zinterstore
Computes the intersection of the provided sorted sets and stores the result in the `destination` key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/zinterstore)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the number of members in the stored intersection.

## Usage

[snippet=zinterstore-1]
> Callback response:

```json
4
```