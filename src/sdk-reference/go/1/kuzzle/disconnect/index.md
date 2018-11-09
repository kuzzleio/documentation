---
layout: sdk.html.hbs
algolia: true
title: disconnect
description: Disconnect the SDK
---

# disconnect

Closes the current connection to Kuzzle.  
The SDK is now in `offline` state.  
A call to `disconnect()` will not trigger a `disconnected` event. This event is only triggered on unexpected disconnection.

## Signature

```go
Disconnect() error
```

## Return

Return a [Kuzzle error]({{ site_base_path }}sdk-reference/essentials/error-handling) if the connection can't be closed.

## Usage

[snippet=disconnect]
