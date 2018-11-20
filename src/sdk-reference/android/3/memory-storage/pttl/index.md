---
layout: sdk.html.hbs
algolia: true
title: pttl
description: MemoryStorage:pttl
---

  

# pttl
Returns the remaining time to live of a key, in milliseconds.

[[_Redis documentation_]](https://redis.io/commands/pttl)


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns an integer containing the remaining time to live of the key, in milliseconds.

## Usage

[snippet=pttl-1]
> Callback response:

```json
43159
```