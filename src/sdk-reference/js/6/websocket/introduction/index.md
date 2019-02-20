---
layout: sdk.html.hbs
title: Introduction
description: Websocket protocol implementation
order: 0
---

# WebSocket

Inherits from: [KuzzleEventEmitter]({{ site_base_path }}sdk-reference/js/6/kuzzle-event-emitter)

The WebSocket protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  

This protocol allows you to use all the features of Kuzzle, including [real-time notifications]({{ site_base_path }}sdk-reference/js/6/realtime-notifications).

## Properties

Available properties.

| Property name        | Type     | Description          | Writable? |
| -------------------- | -------- | --------------------------------------- | :-------: |
| `autoReconnect`      | <pre>boolean</pre> | Automatically reconnect after a connection loss    |    No     |
| `reconnectionDelay`  | <pre>number</pre>  | Number of milliseconds between reconnection attempts         |    No     |

**Notes:**

- updates to `autoReconnect` and `reconnectionDelay` properties will only take effect on next `connect` call
