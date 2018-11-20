---
layout: sdk.html.hbs
algolia: true
title: psetex
description: MemoryStorage:psetex
---

  

# psetex
Sets a key with the provided value, and an expiration delay expressed in milliseconds. If the key does not exist, it is created beforehand.

[[_Redis documentation_]](https://redis.io/commands/psetex)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns null if successful.
## Usage

[snippet=psetex-1]
