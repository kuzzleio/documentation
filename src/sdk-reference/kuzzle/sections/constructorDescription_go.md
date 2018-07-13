## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `protocol` | Connection | The protocol used by the SDK instance  | yes |
| `options` | Options | Kuzzle connection configuration | yes |

### protocol

The protocol used to connect to the Kuzzle instance.  
So far the only protocol available is `websocket`.  
You have to instanciate and pass it to the constructor.   

### options

| Option | Type | Description | Default | Required |
|---------------|---------|----------------------------------------|---------|---------|
| `autoQueue` | boolean | Automatically queue all requests during offline mode | `false` | no |
| `autoReconnect` | boolean | Automatically reconnect after a connection loss | `true` | no |
| `autoReplay` | boolean | Automatically replay queued requests on a `reconnected` event | `false` | no |
| `autoResubscribe` | boolean | Automatically renew all subscriptions on a `reconnected` event | `true` | no |
| `queueTTL` | int | Time a queued request is kept during offline mode, in milliseconds | `120000` | no |
| `queueMaxSize` | int | Number of maximum requests kept during offline mode | `500` | no |
| `replayInterval` | Duration | Delay between each replayed requests, in milliseconds | `10` | no |
| `reconnectionDelay` | Duration | number of milliseconds between reconnection attempts | `1000` | no |
| `sslConnection` | boolean | Switch Kuzzle connection to SSL mode | `false` | no |
| `volatile` | VolatileData | Common volatile data, will be sent to all future requests | - | no |

## Properties

The properties can be writable.  
For example the property `autoQueue` you can read it with `AutoQueue()` and write it with `SetAutoQueue()`

| Property name | Type | Description | Writable? |
|---------------|------|-------------|:---------:|
| `autoQueue` | boolean | Automatically queue all requests during offline mode | Yes |
| `autoReconnect` | boolean | Automatically reconnect after a connection loss | No |
| `autoReplay` | boolean | Automatically replay queued requests on a `reconnected` event |  Yes |
| `autoResubscribe` | boolean | Automatically renew all subscriptions on a `reconnected` event | Yes |
| `host` | string | Target Kuzzle host | No |
| `port` | int | Target Kuzzle port | No |
| `jwt` | string | Token used in requests for authentication. | Yes |
| `offlineQueue` | QueryObject | Contains the queued requests during offline mode | No |
| `offlineQueueLoader` | OfflineQueueLoader | Called before dequeuing requests after exiting offline mode, to add items at the beginning of the offline queue | Yes |
| `queueFilter` | QueueFilter | Called during offline mode. Takes a request object as arguments and returns a boolean, indicating if a request can be queued | Yes |
| `queueMaxSize` | int | Number of maximum requests kept during offline mode | Yes |
| `queueTTL` | Duration | Time a queued request is kept during offline mode, in milliseconds | Yes |
| `replayInterval` | Duration | Delay between each replayed requests | Yes |
| `reconnectionDelay` | Duration | Number of milliseconds between reconnection attempts | No |
| `sslConnection` | boolean | Connect to Kuzzle using SSL | No |
| `volatile` | VolatileData | Common volatile data, will be sent to all future requests | Yes |


**Notes:**

* multiple methods allow passing specific `volatile` data. These `volatile` data will be merged with the global Kuzzle `volatile` object when sending the request, with the request specific `volatile` taking priority over the global ones.
* the `queueFilter` property is a function taking an QueryObject as an argument. This object is the request sent to Kuzzle, following the [Kuzzle API]({{ site_base_path }}api-documentation/query-syntax) format
* if `queueTTL` is set to `0`, requests are kept indefinitely
* The offline buffer acts like a first-in first-out (FIFO) queue, meaning that if the `queueMaxSize` limit is reached, older requests are discarded to make room for new requests
* if `queueMaxSize` is set to `0`, an unlimited number of requests is kept until the buffer is flushed
* the `offlineQueueLoader` must be set with a function, taking no argument, and returning an array of objects containing a `query` member with a Kuzzle query to be replayed, and an optional `cb` member with the corresponding callback to invoke with the query result
* updates to `autoReconnect`, `reconnectionDelay` and `sslConnection` properties will only take effect on next `connect` call
