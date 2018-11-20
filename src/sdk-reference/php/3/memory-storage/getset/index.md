---
layout: sdk.html.hbs
algolia: true
title: getset
description: MemoryStorage:getset
---

  

# getset
Sets a new value for a key and returns its previous value.

[[_Redis documentation_]](https://redis.io/commands/getset)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns the key's previous value.

## Usage

[snippet=getset-1]
> Callback response:

```json
"value"
```