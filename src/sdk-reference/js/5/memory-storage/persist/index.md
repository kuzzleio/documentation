---
layout: sdk.html.hbs
algolia: true
title: persist
description: MemoryStorage:persist
---

  

# persist
Removes the expiration delay or timestamp from a key, making it persistent.

[[_Redis documentation_]](https://redis.io/commands/persist)


## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns a boolean specifying if the operation was successful or not.

## Usage

[snippet=persist-1]
> Callback response:

```json
true
```