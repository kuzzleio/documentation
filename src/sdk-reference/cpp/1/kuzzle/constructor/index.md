---
layout: sdk.html.hbs
title: Constructor
description: Create a new Kuzzle object connected to the backend
order: 0
---

# Constructor

This is the main entry point to communicate with Kuzzle.  
Each instance represent a connection to Kuzzle with specific options.

## Signature

```cpp
Kuzzle(kuzzleio::Protocol* protocol, kuzzleio::options *options = nullptr)
```

## Arguments

| Argument  | Type        | Description                     |
| --------- | ----------- | ------------------------------- |
| `protocol`    | <pre><a href={{ site_base_path }}sdk-reference/cpp/1/protocol/>Protocol</a></pre> | Network protocol configuration |
| `options` | <pre>kuzzleio::options\*</pre>   | Kuzzle connection configuration |


### options

| Option               | Type<br/>(default)               | Description         |
| -------------------- | ------------------ | ------------------------------------------------------------------ | 
| `auto_queue`         | <pre>bool</pre><br/>(`false`)  | Automatically queue all requests during offline mode   |
| `auto_reconnect`     | <pre>bool</pre><br/>(`true`)  | Automatically reconnect after a connection loss         |
| `auto_replay`        | <pre>bool</pre><br/>(`false`)  | Automatically replay queued requests on a `RECONNECTED` event |
| `auto_resubscribe`   | <pre>bool</pre><br/>(`true`)  | Automatically renew all subscriptions on a `RECONNECTED` event |
| `offline_mode`       | <pre>enum Mode</pre><br/>(`MANUAL`) | Offline mode configuration. `MANUAL` or `AUTO` |
| `queue_ttl`          | <pre>unsigned</pre><br/>(`120000`) | Time a queued request is kept during offline mode, in milliseconds |
| `queue_max_size`     | <pre>unsigned long</pre><br/>(`500`) | Number of maximum requests kept during offline mode |
| `replay_interval`    | <pre>unsigned long</pre><br/>(`10`) | Delay between each replayed requests, in milliseconds |
| `reconnection_delay` | <pre>unsigned long</pre><br/>(`10000`) | Number of milliseconds between reconnection attempts |
| `volatile`           | <pre>std::string</pre><br/>(`"{}"`) | JSON string representing common volatile data, will be sent to all future requests |

## Getter & Setter

The properties can be writable.  
For example, you can read the `volatile` property via `getVolatile()` and set it via `setVolatile()`.

| Property name | Type               | Description                                                    | Availability |
| ------------- | ------------------ | -------------------------------------------------------------- | :----------: |
| `autoReplay`  | <pre>bool</pre>    | Automatically replay queued requests on a `reconnected` event  |     Set      |
| `jwt`         | <pre>std::string</pre>  | Token used in requests for authentication.                |     Get      |
| `volatile`    | <pre>std::string</pre>  | Common volatile data, will be sent to all future requests |   Get/Set    |

**Notes:**

- multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
- the `queueFilter` property is a function taking a `query_object` as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api/1/query-syntax) format
- if `queueTTL` is set to `0`, requests are kept indefinitely
- The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
- if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
- the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result
- updates to `autoReconnect`, `reconnectionDelay` and `sslConnection` properties will only take effect on next `connect` call

## Return

A `Kuzzle` instance.

## Usage

[snippet=constructor]
