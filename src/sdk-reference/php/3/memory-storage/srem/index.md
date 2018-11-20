---
layout: sdk.html.hbs
algolia: true
title: srem
description: MemoryStorage:srem
---

  

# srem
Removes members from a set of unique values.

[[_Redis documentation_]](https://redis.io/commands/srem)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of removed elements.

## Usage

[snippet=srem-1]
> Callback response:

```json
2
```