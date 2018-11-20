---
layout: sdk.html.hbs
algolia: true
title: lpop
description: MemoryStorage:lpop
---

  

# lpop
Removes and returns the first element of a list.

[[_Redis documentation_]](https://redis.io/commands/lpop)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns the value of the removed item.

## Usage

[snippet=lpop-1]
> Callback response:

```json
"foo"
```