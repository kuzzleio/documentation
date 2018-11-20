---
layout: sdk.html.hbs
algolia: true
title: rpushx
description: MemoryStorage:rpushx
---

  

# rpushx
Appends the specified value at the end of a list, only if the key already exists and if it holds a list.

[[_Redis documentation_]](https://redis.io/commands/rpushx)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated number of items in the list.

## Usage

[snippet=rpushx-1]
> Callback response:

```json
4
```