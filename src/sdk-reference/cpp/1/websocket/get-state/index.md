---
layout: sdk.html.hbs
title: getState
description: Gets the current state
---

# getState

Gets the current connection state.

## Signature

```cpp
int getState();
```

## Return

The current connection state, values can be from the KuzzleState enum:

```cpp
  KUZZLE_STATE_CONNECTING,
  KUZZLE_STATE_DISCONNECTED,
  KUZZLE_STATE_CONNECTED,
  KUZZLE_STATE_INITIALIZING,
  KUZZLE_STATE_READY,
  KUZZLE_STATE_LOGGUED_OUT,
  KUZZLE_STATE_ERROR,
  KUZZLE_STATE_OFFLINE
```

