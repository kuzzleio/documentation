---
layout: sdk.html.hbs
algolia: true
title: flushdb
description: MemoryStorage:flushdb
---

  

# flushdb
Deletes all the keys of the database dedicated to client applications (the reserved space for Kuzzle is unaffected).

[[_Redis documentation_]](https://redis.io/commands/flushdb)


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `MemoryStorage` object to allow chaining.

---

## Callback Response

Returns null if successful.

## Usage

[snippet=flushdb-1]
