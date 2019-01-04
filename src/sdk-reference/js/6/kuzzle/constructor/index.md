---
layout: sdk.html.hbs
title: Constructor
description: Creates a new Kuzzle object connected to the backend
order: 0
---

# Constructor

This is the main entry point to communicate with Kuzzle.
Each instance represent a connection to Kuzzle with specific options.

## Signature

```javascript
Kuzzle(protocol, [options]);
```

## Arguments

| Argument   | Type               | Description                           |
| ---------- | ------------------ | ------------------------------------- |
| `protocol` | <pre>Protocol</pre> | Protocol used by the SDK instance |
| `options`  | <pre>object</pre> | Kuzzle connection configuration       |

### protocol

The protocol used to connect to the Kuzzle instance.  
It can be one of the following available protocols:
  - [WebSocket]({{ site_base_path }}sdk-reference/js/6/websocket)
  - [Http]({{ site_base_path }}sdk-reference/js/6/http)
  - [SocketIO]({{ site_base_path }}sdk-reference/js/6/socketio)

### options

Kuzzle SDK instance options.

| Property              | <br/>(default)  | Description   |
| -------------- | --------- | ------------- |
| `autoQueue`         | <pre>boolean</pre><br/>(`false`) | Automatically queue all requests during offline mode               | 
| `autoReplay`        | <pre>boolean</pre><br/>(`false`) | Automatically replay queued requests on a `reconnected` event      | 
| `autoResubscribe`   | <pre>boolean</pre><br/>(`true`) | Automatically renew all subscriptions on a `reconnected` event     |  
| `eventTimeout`      | <pre>number</pre><br/>(`200`) | Time (in ms) during which a similar event is ignored               |    
| `offlineMode`       | <pre>string</pre><br/>(`manual`) | Offline mode configuration. Can be `manual` or `auto`            |
| `queueTTL`          | <pre>number</pre><br/>(`120000`) | Time a queued request is kept during offline mode, in milliseconds |
| `queueMaxSize`      | <pre>number</pre><br/>(`500`) | Number of maximum requests kept during offline mode                |   
| `replayInterval`    | <pre>number</pre><br/>(`10`) | Delay between each replayed requests, in milliseconds              |     
| `volatile`          | <pre>object</pre><br/>(`{}`) | Common volatile data, will be sent to all future requests          |     

## Properties

Available properties.

| Property name        | Type     | Description          | Writable? |
| -------------------- | -------- | --------------------------------------- | :-------: |
| `autoQueue`          | <pre>boolean</pre> | Automatically queue all requests during offline mode    |    Yes    |
| `autoReplay`         | <pre>boolean</pre> | Automatically replay queued requests on a `reconnected` event        |    Yes    |
| `autoResubscribe`    | <pre>boolean</pre> | Automatically renew all subscriptions on a `reconnected` event       |    Yes    |
| `jwt`                | <pre>string</pre> | Token used in requests for authentication        |    Yes    |
| `offlineQueue`       | <pre>object[]</pre> | Contains the queued requests during offline mode   |    No     |
| `offlineQueueLoader` | <pre>function</pre> | Called before dequeuing requests after exiting offline mode,</br> to add items at the beginning of the offline queue  |    Yes    |
| `protocol`       | <pre>Protocol</pre> | Protocol used by the SDK   |    No     |
| `queueFilter`        | <pre>function</pre> | Called during offline mode. </br>Takes a request object as arguments and returns a boolean, indicating if a request can be queued |    Yes    |
| `queueMaxSize`       | <pre>number</pre>  | Number of maximum requests kept during offline mode|    Yes    |
| `queueTTL`           | <pre>number</pre>  | Time a queued request is kept during offline mode, in milliseconds      |    Yes    |
| `replayInterval`     | <pre>number</pre>  | Delay between each replayed requests               |    Yes    |
| `volatile`           | <pre>object</pre> | Common volatile data, will be sent to all future requests       |    Yes    |

**Notes:**

- multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
- the `queueFilter` property is a function taking an object as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api/1/essentials/query-syntax) format
- if `queueTTL` is set to `0`, requests are kept indefinitely
- The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
- if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
- the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result

## Return

The `Kuzzle` SDK instance.

## Usage

[snippet=constructor]
