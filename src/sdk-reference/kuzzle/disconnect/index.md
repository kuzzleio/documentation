---
layout: sdk.html
algolia: true
title: disconnect
description: Disconnect the SDK
order: 200
---

# disconnect

Closes the current connection to Kuzzle.  
A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

## Signature
[section=disconnect]
