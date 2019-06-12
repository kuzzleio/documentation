---
code: true
type: page
title: stopQueuing
description: Stops the requests queuing
---

# stopQueuing

Stops the requests queuing.  
Works only in `offline` state, and if the `autoQueue` option is set to false.

## Signature

```cpp
kuzzleio::Kuzzle* stopQueuing() noexcept;
```

## Return

The `Kuzzle` instance.

## Usage

<<< ./snippets/stop-queuing.cpp
