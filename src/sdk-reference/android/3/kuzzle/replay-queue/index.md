---
layout: sdk.html.hbs
algolia: true
title: replayQueue
description: Kuzzle:replayQueue
---
  

# replayQueue
[snippet=replay-queue-1]
Replays the requests queued during offline mode. Works only if the SDK is not in a ``disconnected`` state, and if the ``autoReplay`` option is set to ``false``.

---

## Return Value

Returns the `Kuzzle` SDK object to allow chaining.
