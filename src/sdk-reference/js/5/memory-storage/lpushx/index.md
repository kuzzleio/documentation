---
layout: sdk.html.hbs
algolia: true
title: lpushx
description: MemoryStorage:lpushx
---

  

# lpushx
Prepends the specified value to a list, only if the key already exists and if it holds a list.

[[_Redis documentation_]](https://redis.io/commands/lpushx)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated number of items in the list.

## Usage

[snippet=lpushx-1]
> Callback response:

```json
4
```