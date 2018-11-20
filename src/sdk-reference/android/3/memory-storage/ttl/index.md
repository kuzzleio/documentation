---
layout: sdk.html.hbs
algolia: true
title: ttl
description: MemoryStorage:ttl
---

  

# ttl
Returns the remaining time to live of a key, in seconds, or a negative value if the key does not exist or if it is persistent.

[[_Redis documentation_]](https://redis.io/commands/ttl)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the remaining time to live of the key, in seconds.

## Usage

[snippet=ttl-1]
> Callback response:

```json
42
```