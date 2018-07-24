```cpp
public Kuzzle Kuzzle(std::string host, options options)
```

## Usage

[code-example=constructor]

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `host` | std::string | The target Kuzzle host  | yes |
| `options` | options* | Kuzzle connection configuration | yes |

### hostname

The Kuzzle host to connect to.  
Can be a hostname or an IP address.

### options

| Option | Type | Description | Default | Required |
|---------------|---------|----------------------------------------|---------|---------|
| `auto_queue` | boolean | Automatically queue all requests during offline mode | `false` | no |
| `auto_reconnect` | boolean | Automatically reconnect after a connection loss | `true` | no |
| `auto_replay` | boolean | Automatically replay queued requests on a `reconnected` event | `false` | no |
| `auto_resubscribe` | boolean | Automatically renew all subscriptions on a `reconnected` event | `true` | no |
| `offline_mode` | enum Mode | Offline mode configuration | `MANUAL` | no |
| `queue_ttl` | unsigned | Time a queued request is kept during offline mode, in milliseconds | `120000` | no |
| `queue_max_size` | unsigned long | Number of maximum requests kept during offline mode | `500` | no |
| `replay_interval` | unsigned long | Delay between each replayed requests, in milliseconds | `10` | no |
| `reconnection_delay` | unsigned long | number of milliseconds between reconnection attempts | `1000` | no |
| `volatile` | std::string (json) | Common volatile data, will be sent to all future requests | - | no |

## Getter & Setter

The properties can be writable.  
For example the property `volatile` you can read it with `getVolatile()` and write it with `setVolatile()`

| Property name | Type | Description | Availability |
|---------------|------|-------------|:---------:|
| `autoReplay` | boolean | Automatically replay queued requests on a `reconnected` event | Set |
| `jwt` | std::string | Token used in requests for authentication. | Get |
| `volatile` | std::string (json) | Common volatile data, will be sent to all future requests | Get/Set |

---

## Return

## Exceptions
