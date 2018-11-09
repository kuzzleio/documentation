---
layout: sdk.html.hbs
algolia: true
title: startQueuing
description: Starts the requests queuing
---

# startQueuing

Starts the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```cpp
kuzzleio::Kuzzle* startQueuing()
```

## Return

The `Kuzzle` instance.

## Usage

[snippet=start-queuing]
