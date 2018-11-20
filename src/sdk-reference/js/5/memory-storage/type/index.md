---
layout: sdk.html.hbs
algolia: true
title: type
description: MemoryStorage:type
---

  

# type
Returns the type of the value held by a key.

[[_Redis documentation_]](https://redis.io/commands/type)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns one of the following values: `hash`, `list`, `set`, `string`, `zset`.

## Usage

[snippet=type-1]
> Callback response:

```json
"zset"
```