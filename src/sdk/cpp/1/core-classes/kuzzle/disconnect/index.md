---
code: true
type: page
title: disconnect
description: Disconnect the SDK
---

# disconnect

Closes the current connection to Kuzzle.  
The SDK is now in `offline` state.  
A call to `disconnect()` will not trigger a `DISCONNECTED` event. This event is only triggered on unexpected disconnection.

## Signature

```cpp
void disconnect() noexcept;
```

## Usage

<<< ./snippets/disconnect.cpp
