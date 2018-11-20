---
layout: sdk.html.hbs
algolia: true
title: lrem
description: MemoryStorage:lrem
---

  

# lrem
Removes the first `count` occurences of elements equal to `value` from a list.

[[_Redis documentation_]](https://redis.io/commands/lrem)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of removed elements.

## Usage

[snippet=lrem-1]
> Callback response:

```json
1
```