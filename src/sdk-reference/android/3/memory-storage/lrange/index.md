---
layout: sdk.html.hbs
algolia: true
title: lrange
description: MemoryStorage:lrange
---

  

# lrange
Returns the list elements between the start and stop positions (inclusive).

[[_Redis documentation_]](https://redis.io/commands/lrange)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an array of retrieved values.

## Usage

[snippet=lrange-1]
> Callback response:

```json
[
  "foo",
  "bar"
]
```