---
layout: sdk.html.hbs
algolia: true
title: disconnect
description: Disconnect the SDK
algolia: true
---

# disconnect

Closes the current connection to Kuzzle.
The SDK then enters the `offline` state.
A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

## Arguments

```javascript
disconnect ()
```

## Usage

[snippet=disconnect]
