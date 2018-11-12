---
layout: sdk.html.hbs
algolia: true
title: Constructor
description: Create a new Kuzzle object connected to the backend
---

# Constructor

This is the main entry point to communicate with Kuzzle.  
Each instance represent a connection to Kuzzle with specific options.

## Signature

```javascript
/**
 * @param {string|Protocol} protocol
 * @param {object} [options]
 * @returns {Kuzzle}
 */
Kuzzle(protocol, (options = {}));
```

## Arguments

| Argument   | Type               | Description                           | Required |
| ---------- | ------------------ | ------------------------------------- | -------- |
| `protocol` | string or Protocol | The protocol used by the SDK instance | yes      |
| `options`  | object             | Kuzzle connection configuration       | yes      |

### **protocol**

The protocol used to connect to the Kuzzle instance.  
It can be:

- a string for embedded protocols : `http`, `websocket` or `socketio`
- a custom [Protocol]({{ site_base_path }}/sdk-reference/protocols/create-new-protocol) object

### **options**

| Option              | Type    | Description                                                        | Default  | Required |
| ------------------- | ------- | ------------------------------------------------------------------ | -------- | -------- |
| `autoQueue`         | boolean | Automatically queue all requests during offline mode               | `false`  | no       |
| `autoReconnect`     | boolean | Automatically reconnect after a connection loss                    | `true`   | no       |
| `autoReplay`        | boolean | Automatically replay queued requests on a `reconnected` event      | `false`  | no       |
| `autoResubscribe`   | boolean | Automatically renew all subscriptions on a `reconnected` event     | `true`   | no       |
| `eventTimeout`      | integer | Time (in ms) during which a similar event is ignored               | `200`    | no       |
| `host`              | string  | Kuzzle network host                                                | -        | yes      |
| `port`              | integer | Kuzzle network port                                                | `7512`   | no       |
| `offlineMode`       | string  | Offline mode configuration                                         | `manual` | no       |
| `queueTTL`          | integer | Time a queued request is kept during offline mode, in milliseconds | `120000` | no       |
| `queueMaxSize`      | integer | Number of maximum requests kept during offline mode                | `500`    | no       |
| `replayInterval`    | integer | Delay between each replayed requests, in milliseconds              | `10`     | no       |
| `reconnectionDelay` | integer | number of milliseconds between reconnection attempts               | `1000`   | no       |
| `sslConnection`     | boolean | Switch Kuzzle connection to SSL mode                               | `false`  | no       |
| `volatile`          | object  | Common volatile data, will be sent to all future requests          | -        | no       |

## Properties

| Property name        | Type     | Description                                                                                                                  | Writable? |
| -------------------- | -------- | ---------------------------------------------------------------------------------------------------------------------------- | :-------: |
| `autoQueue`          | boolean  | Automatically queue all requests during offline mode                                                                         |    Yes    |
| `autoReconnect`      | boolean  | Automatically reconnect after a connection loss                                                                              |    No     |
| `autoReplay`         | boolean  | Automatically replay queued requests on a `reconnected` event                                                                |    Yes    |
| `autoResubscribe`    | boolean  | Automatically renew all subscriptions on a `reconnected` event                                                               |    Yes    |
| `host`               | string   | Target Kuzzle host                                                                                                           |    No     |
| `port`               | integer  | Target Kuzzle port                                                                                                           |    No     |
| `jwt`                | string   | Token used in requests for authentication.                                                                                   |    Yes    |
| `offlineQueue`       | object   | Contains the queued requests during offline mode                                                                             |    No     |
| `offlineQueueLoader` | function | Called before dequeuing requests after exiting offline mode, to add items at the beginning of the offline queue              |    Yes    |
| `queueFilter`        | function | Called during offline mode. Takes a request object as arguments and returns a boolean, indicating if a request can be queued |    Yes    |
| `queueMaxSize`       | integer  | Number of maximum requests kept during offline mode                                                                          |    Yes    |
| `queueTTL`           | integer  | Time a queued request is kept during offline mode, in milliseconds                                                           |    Yes    |
| `replayInterval`     | integer  | Delay between each replayed requests                                                                                         |    Yes    |
| `reconnectionDelay`  | integer  | Number of milliseconds between reconnection attempts                                                                         |    No     |
| `sslConnection`      | boolean  | Connect to Kuzzle using SSL                                                                                                  |    No     |
| `volatile`           | object   | Common volatile data, will be sent to all future requests                                                                    |    Yes    |

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
