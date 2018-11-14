---
layout: sdk.html.hbs
algolia: true
title: Constructor
description: Create a new Kuzzle object connected to the backend
order: 1
---

# Constructor

This is the main entry point to communicate with Kuzzle.
Each instance represent a connection to Kuzzle with specific options.

## Signature

```java
Kuzzle(String host)
Kuzzle(String host, io.kuzzle.sdk.Options options)
```

## Arguments

| Argument  | Type    | Description                     | Required |
| --------- | ------- | ------------------------------- | -------- |
| `host`    | String  | The target Kuzzle host          | yes      |
| `options` | io.kuzzle.sdk.Options | Kuzzle connection configuration | yes      |

### **host**

The Kuzzle host to connect to.
Can be a hostname or an IP address.

### **options**

| Option              | Type         | Description                                                        | Default  | Required |
| ------------------- | ------------ | ------------------------------------------------------------------ | -------- | -------- |
| `autoQueue`         | boolean      | Automatically queue all requests during offline mode               | `false`  | no       |
| `autoReconnect`     | boolean      | Automatically reconnect after a connection loss                    | `true`   | no       |
| `autoReplay`        | boolean      | Automatically replay queued requests on a `reconnected` event      | `false`  | no       |
| `autoResubscribe`   | boolean      | Automatically renew all subscriptions on a `reconnected` event     | `true`   | no       |
| `offlineMode`       | Mode         | Offline mode configuration. `MANUAL` or `AUTO`                     | `MANUAL` | no       |
| `queueTTL`          | int          | Time a queued request is kept during offline mode, in milliseconds | `120000` | no       |
| `queueMaxSize`      | int          | Number of maximum requests kept during offline mode                | `500`    | no       |
| `replayInterval`    | Duration     | Delay between each replayed requests, in milliseconds              | `10`     | no       |
| `reconnectionDelay` | Duration     | number of milliseconds between reconnection attempts               | `1000`   | no       |
| `sslConnection`     | boolean      | Switch Kuzzle connection to SSL mode                               | `false`  | no       |
| `volatile`          | VolatileData | Common volatile data, will be sent to all future requests          | -        | no       |

## Getter & Setter

The properties can be writable.
For example, you can read the `volatile` property via `getVolatile()` and set it via `setVolatile()`.

| Property name | Type          | Description                                                   | Availability |
| ------------- | ------------- | ------------------------------------------------------------- | :----------: |
| `autoReplay`  | boolean       | Automatically replay queued requests on a `reconnected` event |     Set      |
| `jwt`         | String        | Token used in requests for authentication.                    |     Get      |
| `volatile`    | String | Common volatile data in JSON format, will be sent to all future requests     |   Get/Set    |

**Notes:**

- multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
- the `queueFilter` property is a function taking a `QueryObject` as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api/1/query-syntax) format
- if `queueTTL` is set to `0`, requests are kept indefinitely
- The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
- if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
- the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result
- updates to `autoReconnect`, `reconnectionDelay` and `sslConnection` properties will only take effect on next `connect` call

## Return

A `io.kuzzle.sdk.Kuzzle` instance.

## Usage

[snippet=constructor]
