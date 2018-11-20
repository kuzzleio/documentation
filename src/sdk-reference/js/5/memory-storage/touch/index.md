---
layout: sdk.html.hbs
algolia: true
title: touch
description: MemoryStorage:touch
---

  

# touch
Alters the last access time of one or multiple keys. A key is ignored if it does not exist.

[[_Redis documentation_]](https://redis.io/commands/touch)


## Options

| Option | Type | Description | Default |
|
## Return value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of altered keys.

## Usage

[snippet=touch-1]
> Callback response:

```json
3
```