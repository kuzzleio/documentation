```java
KuzzleEventEmitter addListener(Event event, EventListener listener)
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `event` | Event | An enum representing the event to listening to  | yes |
| `listener` | EventListener | An instance of an `EventListener` | yes |

### __event__

One of the following event:
```java
Event.CONNECTED
Event.DISCARDED
Event.DISCONNECTED
Event.LOGIN_ATTEMPT
Event.NETWORK_ERROR
Event.OFFLINE_QUEUE_POP
Event.OFFLINE_QUEUE_PUSH
Event.QUERY_ERROR
Event.RECONNECTED
Event.JWT_EXPIRED
Event.ERROR
```

### __listener__

An instance of an `EventListener`.  

## Return

The same `Kuzzle` instance.

## Usage

[code-example=add-listener]
