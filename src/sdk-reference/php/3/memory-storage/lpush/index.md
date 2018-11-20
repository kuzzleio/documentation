---
layout: sdk.html.hbs
algolia: true
title: lpush
description: MemoryStorage:lpush
---

  

# lpush
Prepends the specified values to a list. If the key does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/lpush)


## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated number of items in the list.

## Usage

[snippet=lpush-1]
> Callback response:

```json
6
```