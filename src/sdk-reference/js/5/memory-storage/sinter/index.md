---
layout: sdk.html.hbs
algolia: true
title: sinter
description: MemoryStorage:sinter
---

  

# sinter
Returns the intersection of the provided sets of unique values.

[[_Redis documentation_]](https://redis.io/commands/sinter)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of values in the computed intersection.

## Usage

[snippet=sinter-1]
> Callback response:

```json
[
  "intersection value1",
  "intersection value2",
  "..."
]
```