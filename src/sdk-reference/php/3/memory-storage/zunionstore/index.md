---
layout: sdk.html.hbs
algolia: true
title: zunionstore
description: MemoryStorage:zunionstore
---

  

# zunionstore
Computes the union of the provided sorted sets and stores the result in the `destination` key.

If the destination key already exists, it is overwritten.

[[_Redis documentation_]](https://redis.io/commands/zunionstore)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the number of members in the stored union.

## Usage

[snippet=zunionstore-1]
> Callback response:

```json
4
```