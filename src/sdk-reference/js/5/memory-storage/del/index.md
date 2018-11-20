---
layout: sdk.html.hbs
algolia: true
title: del
description: MemoryStorage:del
---

  

# del
Deletes a list of keys.

[[_Redis documentation_]](https://redis.io/commands/del)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Return an integer containing the number of deleted keys.

## Usage

[snippet=del-1]
> Callback response:

```json
3
```