---
layout: sdk.html.hbs
algolia: true
title: zincrby
description: MemoryStorage:zincrby
---

  

# zincrby
Increments the score of a `member` in a sorted set by the provided `value`.

[[_Redis documentation_]](https://redis.io/commands/zincrby)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a double containing the updated member's score in the sorted set.

## Usage

[snippet=zincrby-1]
> Callback response:

```json
4.14159
```