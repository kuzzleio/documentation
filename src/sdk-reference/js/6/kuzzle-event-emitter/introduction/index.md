---
layout: sdk.html.hbs
title: Introduction
description: KuzzleEventEmitter class
order: 0
---

# KuzzleEventEmitter

This is the base class that emulate Node.js `EventEmitter` for both client and server side code.  

There is several class extending `KuzzleEventEmitter` in the SDK:
 - [Kuzzle]({{ site_base_path }}sdk-reference/js/6/kuzzle)
 - [WebSocket]({{ site_base_path }}sdk-reference/js/6/websocket)
 - [Http]({{ site_base_path }}sdk-reference/js/6/http)
 - [SocketIO]({{ site_base_path }}sdk-reference/js/6/socketio)
