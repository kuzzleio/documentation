---
layout: sdk.html.hbs
algolia: true
title: sunion
description: MemoryStorage:sunion
---

  

# sunion
Returns the union of the provided sets of unique values.

[[_Redis documentation_]](https://redis.io/commands/sunion)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of values in the computed union.

## Usage

[snippet=sunion-1]
> Callback response:

```json
[
  "union value1",
  "union value2",
  "..."
]
```