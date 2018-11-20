---
layout: sdk.html.hbs
algolia: true
title: pfmerge
description: MemoryStorage:pfmerge
---

  

# pfmerge
Merges multiple [HyperLogLog](https://en.wikipedia.org/wiki/HyperLogLog) data structures into an unique HyperLogLog structure stored at `key`, approximating the cardinality of the union of the source structures.

[[_Redis documentation_]](https://redis.io/commands/pfmerge)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns null if successful.
## Usage

[snippet=pfmerge-1]
