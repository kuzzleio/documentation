---
layout: sdk.html.hbs
algolia: true
title: disconnect
description: Disconnect the SDK
algolia: true
---

# disconnect

Closes the current connection to Kuzzle.  
The SDK is now in `offline` state.  
A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

## Signature

```cpp
void disconnect()
```

## Usage

[snippet=disconnect]
