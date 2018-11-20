---
layout: sdk.html.hbs
algolia: true
title: linsert
description: MemoryStorage:linsert
---

  

# linsert
Inserts a value in a list, either before or after the reference pivot value.

[[_Redis documentation_]](https://redis.io/commands/linsert)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated number of items in the list.

## Usage

[snippet=linsert-1]
> Callback response:

```json
4
```