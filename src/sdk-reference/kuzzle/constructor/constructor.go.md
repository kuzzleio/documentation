```go
NewKuzzle(protocol connection.Connection) (*Kuzzle, error)
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `protocol` | connection.Connection | The protocol used by the SDK instance  | yes |

### __protocol__

A [Protocol]({{ site_base_path }}/sdk-reference/protocols/create-new-protocol) is a structure implementing the `connection.Connection` interface.
The available protocols are:
 - `websocket.Websocket`  

The protocol must be instantiated and passed to the constructor.
It takes the following arguments:

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `host` | string | Kuzzle hostname to connect to | yes |
| `options` | types.Options | Kuzzle connection configuration | yes |

The `options` parameter of the protocol constructor has the following properties.  
You can use standard getter/setter to use these properties.  

| Option | Type | Description | Default | Required |
|---------------|---------|----------------------------------------|---------|---------|
| `autoQueue` | boolean | Automatically queue all requests during offline mode | `false` | no |
| `autoReconnect` | boolean | Automatically reconnect after a connection loss | `true` | no |
| `autoReplay` | boolean | Automatically replay queued requests on a `reconnected` event | `false` | no |
| `autoResubscribe` | boolean | Automatically renew all subscriptions on a `reconnected` event | `true` | no |
| `offlineMode` | int | Offline mode configuration | `types.Manual` | no |
| `port` | int | Target Kuzzle port | `7512` | no |
| `queueTTL` | int | Time a queued request is kept during offline mode, in milliseconds | `120000` | no |
| `queueMaxSize` | int | Number of maximum requests kept during offline mode | `500` | no |
| `replayInterval` | Duration | Delay between each replayed requests, in milliseconds | `10` | no |
| `reconnectionDelay` | Duration | number of milliseconds between reconnection attempts | `1000` | no |
| `sslConnection` | boolean | Switch Kuzzle connection to SSL mode | `false` | no |
| `volatile` | VolatileData | Common volatile data, will be sent to all future requests | - | no |

## Getter & Setter

These properties of the Kuzzle struct can be writable.  
For example, you can read the `volatile` property via `getVolatile()` and set it via `setVolatile()`.  

| Property name | Type | Description | Availability |
|---------------|------|-------------|:---------:|
| `autoQueue` | boolean | Automatically queue all requests during offline mode | Get/Set |
| `autoReconnect` | boolean | Automatically reconnect after a connection loss | Get |
| `autoReplay` | boolean | Automatically replay queued requests on a `reconnected` event |  Get/Set |
| `autoResubscribe` | boolean | Automatically renew all subscriptions on a `reconnected` event | Get/Set |
| `host` | string | Target Kuzzle host | Get |
| `port` | int | Target Kuzzle port | Get |
| `jwt` | string | Token used in requests for authentication. | Get/Set |
| `offlineQueue` | QueryObject | Contains the queued requests during offline mode | Get |
| `offlineQueueLoader` | OfflineQueueLoader | Called before dequeuing requests after exiting offline mode, to add items at the beginning of the offline queue | Get/Set |
| `queueFilter` | QueueFilter | Called during offline mode. Takes a request object as arguments and returns a boolean, indicating if a request can be queued | Get/Set |
| `queueMaxSize` | int | Number of maximum requests kept during offline mode | Get/Set |
| `queueTTL` | Duration | Time a queued request is kept during offline mode, in milliseconds | Get/Set |
| `replayInterval` | Duration | Delay between each replayed requests | Get/Set |
| `reconnectionDelay` | Duration | Number of milliseconds between reconnection attempts | Get |
| `sslConnection` | boolean | Connect to Kuzzle using SSL | Get |
| `volatile` | VolatileData | Common volatile data, will be sent to all future requests | Get/Set |


**Notes:**

* multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
* the `queueFilter` property is a function taking a `QueryObject` as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api-documentation/query-syntax) format
* if `queueTTL` is set to `0`, requests are kept indefinitely
* The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
* if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
* the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result
* updates to `autoReconnect`, `reconnectionDelay` and `sslConnection` properties will only take effect on next `connect` call

## Return

A `Kuzzle` struct and an [error struct]({{ site_base_path }}sdk-reference/essentials/error-handling).  
The `error` struct is nil if everything was ok.  

## Usage

In a first step, you have to create a new `connection.Connection` and pass it to the constructor.  
By now the only connection available is `websocket.Websocket`.

[code-example=constructor]
