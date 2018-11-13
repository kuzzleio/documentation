---
layout: sdk.html.hbs
algolia: true
title: Constructor
description: Creates a new Kuzzle object connected to the backend
---

# Constructor

This is the main entry point to communicate with Kuzzle.
Each instance represent a connection to Kuzzle with specific options.

## Arguments

```javascript
Kuzzle(protocol, [options]);
```

<br/>

| Argument   | Type               | Description                           |
| ---------- | ------------------ | ------------------------------------- |
| `protocol` | <pre>string</pre> | Protocol used by the SDK instance |
| `options`  | <pre>object</pre> | Kuzzle connection configuration       |

### protocol

The protocol used to connect to the Kuzzle instance.
It can be:

- a string for embedded protocols : `http`, `websocket` or `socketio`
- a custom [Protocol]({{ site_base_path }}/sdk-reference/js/6/protocols/create-new-protocol) object

### options

| Option              | Type    | Description   | Default  | Required |
| -------------- | --------- | ------------- | -------- | -------- |
| `autoQueue`         | <pre>boolean</pre> | Automatically queue all requests during offline mode               | `false`  | no       |
| `autoReconnect`     | <pre>boolean</pre> | Automatically reconnect after a connection loss                    | `true`   | no       |
| `autoReplay`        | <pre>boolean</pre> | Automatically replay queued requests on a `reconnected` event      | `false`  | no       |
| `autoResubscribe`   | <pre>boolean</pre> | Automatically renew all subscriptions on a `reconnected` event     | `true`   | no       |
| `eventTimeout`      | <pre>number</pre> | Time (in ms) during which a similar event is ignored               | `200`    | no       |
| `host`              | <pre>string</pre> | Kuzzle network host                                                | -        | yes      |
| `port`              | <pre>number</pre> | Kuzzle network port                                                | `7512`   | no       |
| `offlineMode`       | <pre>string</pre> | Offline mode configuration                                         | `manual` | no       |
| `queueTTL`          | <pre>number</pre> | Time a queued request is kept during offline mode, in milliseconds | `120000` | no       |
| `queueMaxSize`      | <pre>number</pre> | Number of maximum requests kept during offline mode                | `500`    | no       |
| `replayInterval`    | <pre>number</pre> | Delay between each replayed requests, in milliseconds              | `10`     | no       |
| `reconnectionDelay` | <pre>number</pre> | number of milliseconds between reconnection attempts               | `1000`   | no       |
| `sslConnection`     | <pre>boolean</pre> | Switch Kuzzle connection to SSL mode                               | `false`  | no       |
| `volatile`          | <pre>number</pre> | Common volatile data, will be sent to all future requests          | -        | no       |

## Properties

| Property name        | Type     | Description          | Writable? |
| -------------------- | -------- | --------------------------------------- | :-------: |
| `autoQueue`          | <pre>boolean</pre> | Automatically queue all requests during offline mode    |    Yes    |
| `autoReconnect`      | <pre>boolean</pre> | Automatically reconnect after a connection loss    |    No     |
| `autoReplay`         | <pre>boolean</pre> | Automatically replay queued requests on a `reconnected` event        |    Yes    |
| `autoResubscribe`    | <pre>boolean</pre> | Automatically renew all subscriptions on a `reconnected` event       |    Yes    |
| `host`               | <pre>string</pre> | Target Kuzzle host   |    No     |
| `port`               | <pre>number</pre>  | Target Kuzzle port                                 |    No     |
| `jwt`                | <pre>string</pre> | Token used in requests for authentication        |    Yes    |
| `offlineQueue`       | <pre>object</pre> | Contains the queued requests during offline mode   |    No     |
| `offlineQueueLoader` | <pre>function</pre> | Called before dequeuing requests after exiting offline mode,</br> to add items at the beginning of the offline queue  |    Yes    |
| `queueFilter`        | <pre>function</pre> | Called during offline mode. </br>Takes a request object as arguments and returns a boolean, indicating if a request can be queued |    Yes    |
| `queueMaxSize`       | <pre>number</pre>  | Number of maximum requests kept during offline mode|    Yes    |
| `queueTTL`           | <pre>number</pre>  | Time a queued request is kept during offline mode, in milliseconds      |    Yes    |
| `replayInterval`     | <pre>number</pre>  | Delay between each replayed requests               |    Yes    |
| `reconnectionDelay`  | <pre>number</pre>  | Number of milliseconds between reconnection attempts         |    No     |
| `sslConnection`      | <pre>boolean</pre> | Connect to Kuzzle using SSL                        |    No     |
| `volatile`           | <pre>object</pre> | Common volatile data, will be sent to all future requests       |    Yes    |

**Notes:**

- multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
- the `queueFilter` property is a function taking an object as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api/1/query-syntax) format
- if `queueTTL` is set to `0`, requests are kept indefinitely
- The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
- if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
- the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result
- updates to `autoReconnect`, `reconnectionDelay` and `sslConnection` properties will only take effect on next `connect` call

## Return

The `Kuzzle` instance.

## Usage

[snippet=constructor]
