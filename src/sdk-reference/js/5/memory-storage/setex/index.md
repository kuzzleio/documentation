---
layout: sdk.html.hbs
algolia: true
title: setex
description: MemoryStorage:setex
---

  

# setex
Sets a key with the provided value, and an expiration delay expressed in seconds. If the key does not exist, it is created beforehand.

[[_Redis documentation_]](https://redis.io/commands/setex)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns null if successful.
## Usage

[snippet=setex-1]
