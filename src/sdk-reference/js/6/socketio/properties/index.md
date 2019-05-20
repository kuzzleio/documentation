---
layout: sdk.html.hbs
title: Properties
description: SocketIO class properties
order: 100
---

# Properties

| Property name        | Type     | Description          |
| -------------------- | -------- | ---------------------|
| `host`  | <pre>string</pre>  | Hostname |
| `port`  | <pre>number</pre>  | Port |
| `ssl`  | <pre>boolean</pre>  | `true` if ssl is active |
| `autoReconnect` | <pre>boolean</pre> | Automatically reconnect after a connection loss |
| `reconnectionDelay` | <pre>number</pre>  | Number of milliseconds between reconnection attempts |
| `connected`  | <pre>boolean</pre>  | Returns `true` if the socket is open |

<div class="alert alert-info">
Updates to <code>autoReconnect</code> and <code>reconnectionDelay</code> properties will only take effect on the next `connect` call.
</div>
