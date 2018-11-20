---
layout: sdk.html.hbs
algolia: true
title: geoadd
description: MemoryStorage:geoadd
---

  

# geoadd
Adds geospatial points to the specified key.

[[_Redis documentation_]](https://redis.io/commands/geoadd)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns an integer containing the number of points added to the key.

## Usage

[snippet=geoadd-1]
> Callback response:

```json
2
```