---
layout: sdk.html.hbs
algolia: true
title: sdiff
description: MemoryStorage:sdiff
---

  

# sdiff
Returns the difference between the set of unique values stored at `key` and the other provided sets.

[[_Redis documentation_]](https://redis.io/commands/sdiff)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of differences.

## Usage

[snippet=sdiff-1]
> Callback response:

```json
[
  "diff value1",
  "diff value2",
  "..."
]
```