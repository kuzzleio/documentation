---
layout: sdk.html.hbs
title: disconnect
description: Kuzzle:disconnect
---
  

# disconnect
Closes the current connection, and frees all allocated resources.  
Contrary to the `offline` state (when the network connection is unexpectedly lost), `disconnect()`  invalidates the instance, which cannot be used until [connect()]({{ site_base_path }}sdk-reference/php/3/kuzzle/connect) is explicitly called.  
This action does not trigger a `disconnected` event since this event is triggered when an unexpected disconnection occur.  

## Usage

[snippet=disconnect-1]
