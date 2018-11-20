---
layout: sdk.html.hbs
algolia: true
title: hincrbyfloat
description: MemoryStorage:hincrbyfloat
---

  

# hincrbyfloat
Increments the number stored in a hash field by the provided float value.

[[_Redis documentation_]](https://redis.io/commands/hincrbyfloat)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns the newly incremented value, as a floating point number.

## Usage

[snippet=hincrbyfloat-1]
> Callback response:

```json
48.14159
```