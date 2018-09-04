---
layout: sdk.html
algolia: true
title: playQueue
description: Play the offline request queue
order: 200
---

# playQueue

Play the requests queued during `offline` state.  
Works only if the SDK is not in a `offline` state, and if the `autoReplay` option is set to `false`.

## Signature

```java
Kuzzle playQueue()
```

## Return

The `Kuzzle` instance.

## Usage

[code-example=play-queue]
