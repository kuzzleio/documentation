---
layout: sdk.html.hbs
title: Introduction
description: SocketIO protocol implementation
order: 0
---

# SocketIO

The SocketIO protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  
This protocol allows you to use all the features of Kuzzle, including [real-time notifications]({{ site_base_path }}sdk-reference/js/6/realtime-notifications).

<div class="alert alert-info">
  <p>
  The SocketIO protocol is used for websocket compatibility with older browsers. It is preferable to use the <a href="{{ site_base_path }}sdk-reference/js/6/websocket">WebSocket</a> protocol when possible.
  </p>
</div>
