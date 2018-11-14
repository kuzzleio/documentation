---
layout: sdk.html.hbs
algolia: true
title: disconnect
description: Kuzzle:disconnect
---
  

# disconnect

[snippet=disconnect-1]
Closes the current connection, and frees all allocated resources.  
Contrary to the `offline` state (when the network connection is unexpectedly lost), `disconnect()`  invalidates the instance, which cannot be used until [connect()]({{ site_base_path }}sdk-reference/kuzzle/connect) is explicitly called.  
This action does not trigger a `disconnected` event since this event is triggered when an unexpected disconnection occur.  
