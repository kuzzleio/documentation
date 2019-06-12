---
code: true
type: page
title: flushQueue
description: Empties the offline request queue
---

# flushQueue

Empties the offline request queue without playing it.

## Signature

```cpp
kuzzleio::Kuzzle* flushQueue() noexcept;
```

## Return

The `Kuzzle` instance.

## Usage

<<< ./snippets/flush-queue.cpp
