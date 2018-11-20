---
layout: sdk.html.hbs
algolia: true
title: append
description: MemoryStorage:append
---

  

# append
Appends a value to a key. If the key does not exist, it is created.

[[_Redis documentation_]](https://redis.io/commands/append)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Return an integer containing the new length of the key's value.

## Usage

[snippet=append-1]
> Callback response:

```json
5
```