---
layout: sdk.html.hbs
title: Constructor
description: Creates a new Http protocol
order: 0
---

# Constructor

The Http protocol can be used by an instance of the SDK to communicate with your Kuzzle server.  

<div class="alert alert-info">
  <p>
  This protocol does not allow to use the <a href="{{ site_base_path }}sdk-reference/js/6/realtime-notifications">real-time notifications</a>. 
  </p>
  <p>
  You have to use <a href="{{ site_base_path }}sdk-reference/js/6/websocket">WebSocket</a> or <a href="{{ site_base_path }}sdk-reference/js/6/socketio">SocketIO</a> protocol instead.
  </p>
</div>

## Signature

```javascript
Http(host, [options]);
```

## Arguments

| Argument   | Type               | Description                           |
| ---------- | ------------------ | ------------------------------------- |
| `host` | <pre>string</pre> | Kuzzle server hostname or IP |
| `options`  | <pre>object</pre> | Http connection options       |

### options

Http protocol connection options.

| Property              | Type<br/>(default)  | Description   |
| -------------- | --------- | ------------- |
| `port`         | <pre>number</pre><br/>(`7512`) | Kuzzle server port               | 
| `sslConnection`     | <pre>boolean</pre><br/>(`false`) | Use SSL to connect to Kuzzle server                    |   

## Return

A `Http` protocol instance.

## Usage

[snippet=constructor]
