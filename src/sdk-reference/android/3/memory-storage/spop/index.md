---
layout: sdk.html.hbs
algolia: true
title: spop
description: MemoryStorage:spop
---

  

# spop
Removes and returns one or more elements at random from a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/spop)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of removed elements.

## Usage

[snippet=spop-1]
> Callback response:

```json
[ "removed element" ]
```