---
layout: sdk.html.hbs
algolia: true
title: smembers
description: MemoryStorage:smembers
---

  

# smembers
Returns the members of a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/smembers)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an array of values held by the provided set.

## Usage

[snippet=smembers-1]
> Callback response:

```json
[
  "member1",
  "member2",
  "..."
]
```