---
layout: sdk.html.hbs
algolia: true
title: playQueue
description: Play the offline request queue
algolia: true
---

# playQueue

Play the requests queued during `offline` state.  
Works only if the SDK is not in a `offline` state, and if the `autoReplay` option is set to `false`.

## Signature

```cpp
kuzzleio::Kuzzle* playQueue()
```

## Return

The `Kuzzle` instance.

## Usage

[snippet=play-queue]
