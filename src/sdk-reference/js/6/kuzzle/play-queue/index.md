---
layout: sdk.html.hbs
algolia: true
title: playQueue
description: Play the offline request queue
algolia: true
---

# playQueue

Plays the requests queued during `offline` state.
Works only if the SDK is not in a `offline` state, and if the `autoReplay` option is set to `false`.

## Arguments

```javascript
playQueue ()
```

## Return

The `Kuzzle` instance.

## Usage

[snippet=play-queue]
