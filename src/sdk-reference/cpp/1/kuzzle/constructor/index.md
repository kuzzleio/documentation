---
layout: sdk.html.hbs
title: Constructor
description: Create a new Kuzzle object connected to the backend
order: 50
---

# Constructor

Use this constructor to create a new instance of the SDK.  
Each instance represent a different connection to a Kuzzle server with specific options.

## Signature

```cpp
kuzzleio::Kuzzle(kuzzleio::Protocol* protocol);

kuzzleio::Kuzzle(kuzzleio::Protocol* protocol, const options& options);
```

## Arguments

| Argument  | Type        | Description                     |
| --------- | ----------- | ------------------------------- |
| `protocol`    | <pre><a href={{ site_base_path }}sdk-reference/cpp/1/protocol/>Protocol\*</a></pre> | Protocol used by the SDK instance |
| `options` | <pre>const kuzzleio::options\&</pre>   | Kuzzle object configuration |

### protocol

The protocol used to connect to the Kuzzle instance.  
It can be one of the following available protocols:
  - [WebSocket]({{ site_base_path }}sdk-reference/cpp/1/websocket)


### options

Kuzzle SDK instance options.


| Option               | Type<br/>(default)               | Description         |
| -------------------- | ------------------ | ------------------------------------------------------------------ | 
| `auto_queue`         | <pre>bool</pre><br/>(`false`)  | Automatically queue all requests during offline mode   |
| `auto_reconnect`     | <pre>bool</pre><br/>(`true`)  | Automatically reconnect after a connection loss         |
| `auto_replay`        | <pre>bool</pre><br/>(`false`)  | Automatically replay queued requests on a `EVENT_RECONNECTED` event |
| `auto_resubscribe`   | <pre>bool</pre><br/>(`true`)  | Automatically renew all subscriptions on a `EVENT_RECONNECTED` event |
| `offline_mode`       | <pre>enum Mode</pre><br/>(`MANUAL`) | Offline mode configuration. `MANUAL` or `AUTO` |
| `queue_ttl`          | <pre>unsigned int</pre><br/>(`120000`) | Time a queued request is kept during offline mode, in milliseconds |
| `queue_max_size`     | <pre>unsigned long</pre><br/>(`500`) | Number of maximum requests kept during offline mode |
| `replay_interval`    | <pre>unsigned long</pre><br/>(`10`) | Delay between each replayed requests, in milliseconds |
| `reconnection_delay` | <pre>unsigned long</pre><br/>(`10000`) | Number of milliseconds between reconnection attempts |
| `volatile`           | <pre>std::string</pre><br/>(`"{}"`) | JSON string representing common volatile data, will be sent to all future requests |

**Notes:**

- if `queue_ttl` is set to `0`, requests are kept indefinitely
- The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queue_max_size` limit is reached, older requests are discarded to make room for new requests
- if `queue_max_size` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
- multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.

## Return

A `Kuzzle` instance.

## Usage

[snippet=constructor]
