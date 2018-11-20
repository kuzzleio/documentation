---
layout: sdk.html.hbs
algolia: true
title: rpush
description: MemoryStorage:rpush
---

  

# rpush
Appends the specified values at the end of a list. If the key does not exist, it is created holding an empty list before performing the operation.

[[_Redis documentation_]](https://redis.io/commands/rpush)


## Options

| Option | Type | Description | Default |
|
## Return value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the updated number of items in the list.

## Usage

[snippet=rpush-1]
> Callback response:

```json
6
```