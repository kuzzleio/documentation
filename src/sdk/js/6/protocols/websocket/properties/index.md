---
code: false
type: page
title: Properties
description: Websocket class properties
order: 10
---

# Properties

| Property name        | Type     | Description          |
| -------------------- | -------- | ---------------------|
| `autoReconnect`      | <pre>boolean</pre> | Automatically reconnect after a connection loss    |
| `reconnectionDelay`  | <pre>number</pre>  | Number of milliseconds between reconnection attempts         |

::: info
Updates to `autoReconnect` and `reconnectionDelay` properties will only take effect on the next `connect` call.
:::