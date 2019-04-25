---
layout: full.html.hbs
title: WebSocket
order: 0
---
# WebSocket

## Configuration

The protocol can be configured via the [kuzzlerc configuration file]({{ site_base_path }}guide/1/essentials/configuration/), under the ``server > protocols > websocket`` section.

| Option | Type | Description | Default |
|---|---|---|---|
| ``enabled`` | <pre>boolean</pre> | Enables/Disables WebSocket protocol support | ``true`` |

### Configure listening port

<div class="alert alert-warning">
HTTP, WebSocket and Socket IO protocols share the same underlying server instance. Modifying the listening port will impact all these three protocols.
</div>

By default, Kuzzle listens to the ``7512`` port.

The port can be modified under the ``server > port`` section of [Kuzzle configuration]({{ site_base_path }}guide/1/essentials/configuration/).
