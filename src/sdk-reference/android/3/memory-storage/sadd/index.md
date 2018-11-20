---
layout: sdk.html.hbs
algolia: true
title: sadd
description: MemoryStorage:sadd
---

  

# sadd
Adds members to a set of unique values stored at `key`. If the `key` does not exist, it is created beforehand.

[[_Redis documentation_]](https://redis.io/commands/sadd)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of added elements to the set.

## Usage

[snippet=sadd-1]
> Callback response:

```json
6
```