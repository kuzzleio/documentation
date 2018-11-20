---
layout: sdk.html.hbs
algolia: true
title: hmget
description: MemoryStorage:hmget
---

  

# hmget
Returns the values of the specified hash’s fields.

[[_Redis documentation_]](https://redis.io/commands/hmget)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array containing the specified fields values.

## Usage

[snippet=hmget-1]
> Callback response:

```json
[
  "field1's value",
  "field2's value",
  "..."
]
```