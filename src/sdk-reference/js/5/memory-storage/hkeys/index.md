---
layout: sdk.html.hbs
algolia: true
title: hkeys
description: MemoryStorage:hkeys
---

  

# hkeys
Returns all field names contained in a hash.

[[_Redis documentation_]](https://redis.io/commands/hkeys)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of field names.

## Usage

[snippet=hkeys-1]
> Callback response:

```json
[
  "field1",
  "field2",
  "..."
]
```