---
layout: sdk.html
algolia: true
title: disconnect
description: Disconnect the SDK
order: 200
---

# disconnect

Closes the current connection to Kuzzle.  
The SDK is now in `offline` state.  
A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

## Signature

```javascript
/**
 * @returns {undefined}
 */
disconnect();
```

## Usage

[code-example=disconnect]
