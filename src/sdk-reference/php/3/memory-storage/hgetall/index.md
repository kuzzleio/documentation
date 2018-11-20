---
layout: sdk.html.hbs
algolia: true
title: hgetall
description: MemoryStorage:hgetall
---

  

# hgetall
Returns all fields and values of a hash.

[[_Redis documentation_]](https://redis.io/commands/hgetall)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a JSON object containing the fields and values of a hash.

## Usage

[snippet=hgetall-1]
> Callback response:

```json
{
  "field1": "value",
  "field2": "value",
  "...": "..."
}
```