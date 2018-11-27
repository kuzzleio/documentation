---
layout: sdk.html.hbs
algolia: true
title: replayQueue
description: Kuzzle:replayQueue
algolia: true
---
  

# replayQueue
Replays the requests queued during offline mode. Works only if the SDK is not in a ``disconnected`` state, and if the ``autoReplay`` option is set to ``false``.

---

## Return Value

Returns the `Kuzzle` SDK object to allow chaining.

## Usage

[snippet=replay-queue-1]
