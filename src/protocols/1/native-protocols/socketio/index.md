---
layout: full.html.hbs
title: SocketIO
order: 0
---
# Socket IO

## Configuration

The protocol can be configured via [Kuzzle'rc configuration]({{ site_base_path }}guide/1/essentials/configuration/), under ``server > protocols > socketio`` section.

| Option | Type | Description | Default |
|---|---|---|---|
| ``enabled`` | <pre>boolean</pre> | Enable/Disable Socket.IO protocol support | ``true`` |
| ``origins`` | <pre>string</pre> | Value of Access-Control-Allow-Origin header to answer the upgrade request | ``*:*`` |

### Configure listening port

<div class="alert alert-warning">
HTTP, WebSocket and Socket.IO protocols share the same underlying server instance. Modifying the listening port will impact all these three protocols.
</div>

By default, Kuzzle listens to the ``7512`` port.

The port can be modified under the ``server > port`` section of [Kuzzle configuration]({{ site_base_path }}guide/1/essentials/configuration/).
