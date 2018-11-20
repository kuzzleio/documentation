---
layout: sdk.html.hbs
algolia: true
title: mget
description: MemoryStorage:mget
---

  

# mget
Returns the values of the provided keys.

[[_Redis documentation_]](https://redis.io/commands/mget)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of the specified keys' values.

## Usage

[snippet=mget-1]
> Callback response:

```json
[
  "key1's value",
  "key2's value",
  "..."
]
```