---
layout: sdk.html.hbs
title: Introduction
description: Http protocol implementation
order: 0
---

# Http

The Http protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  

<div class="alert alert-info">
  <p>
  This protocol does not allow to use the <a href="{{ site_base_path }}sdk-reference/js/6/realtime-notifications">real-time notifications</a>. 
  </p>
  <p>
  You have to use <a href="{{ site_base_path }}sdk-reference/js/6/websocket">WebSocket</a> or <a href="{{ site_base_path }}sdk-reference/js/6/socketio">SocketIO</a> protocol instead.
  </p>
</div>
