---
code: true
type: page
title: playQueue
description: Play the offline request queue
---

# playQueue

Play the requests queued during `offline` state.  
Works only if the SDK is not in a `offline` state, and if the `autoReplay` option is set to `false`.

## Signature

```cpp
kuzzleio::Kuzzle* playQueue() noexcept;
```

## Return

The `Kuzzle` instance.

## Usage

<<< ./snippets/play-queue.cpp
