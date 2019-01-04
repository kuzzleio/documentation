---
layout: sdk.html.hbs
title: Constructor
description: Creates a new WebSocket protocol
order: 0
---

# Constructor

The WebSocket protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  
This protocol allows you to use all the features of Kuzzle, including [real-time notifications]({{ site_base_path }}sdk-reference/js/6/realtime-notifications).

## Signature

```javascript
WebSocket(host, [options]);
```

## Arguments

| Argument   | Type               | Description                           |
| ---------- | ------------------ | ------------------------------------- |
| `host` | <pre>string</pre> | Kuzzle server hostname or IP |
| `options`  | <pre>object</pre> | WebSocket connection options       |

### options

WebSocket protocol connection options.

| Property              | <br/>(default)  | Description   |
| -------------- | --------- | ------------- |
| `port`         | <pre>number</pre><br/>(`7512`) | Kuzzle server port               | 
| `sslConnection`     | <pre>boolean</pre><br/>(`false`) | Use SSL to connect to Kuzzle server                    |   
| `autoReconnect`     | <pre>boolean</pre><br/>(`true`) | Automatically reconnect to kuzzle after a `disconnected` event      | 
| `reconnectionDelay` | <pre>number</pre><br/>(`1000`) | number of milliseconds between reconnection attempts               |  

## Properties

Available properties.

| Property name        | Type     | Description          | Writable? |
| -------------------- | -------- | --------------------------------------- | :-------: |
| `autoReconnect`      | <pre>boolean</pre> | Automatically reconnect after a connection loss    |    No     |
| `reconnectionDelay`  | <pre>number</pre>  | Number of milliseconds between reconnection attempts         |    No     |

**Notes:**

- updates to `autoReconnect` and `reconnectionDelay` properties will only take effect on next `connect` call

## Return

A `WebSocket` protocol instance.

## Usage

[snippet=constructor]
