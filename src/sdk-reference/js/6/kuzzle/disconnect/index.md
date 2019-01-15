---
layout: sdk.html.hbs
title: disconnect
description: Disconnect the SDK
---

# disconnect

Closes the current connection to Kuzzle.
The SDK then enters the `offline` state.
A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

## Signature

```javascript
disconnect ()
```

## Usage

[snippet=disconnect]
