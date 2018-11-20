---
layout: sdk.html.hbs
algolia: true
title: rpoplpush
description: MemoryStorage:rpoplpush
---

  

# rpoplpush
Removes the last element of the list at `source` and pushes it back at the start of the list at `destination`.

[[_Redis documentation_]](https://redis.io/commands/rpoplpush)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns the value of the removed and pushed item.

## Usage

[snippet=rpoplpush-1]
> Callback response:

```json
"foo"
```