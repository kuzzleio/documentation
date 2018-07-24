```java
public KuzzleEventEmitter addListener(Event event, EventListener listener)
```

## Usage

[code-example=add-listener]

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `event` | Event | An enum representing the event to listening to  | yes |
| `listener` | EventListener | An instance of an `EventListener` | yes |

### event

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

### listener

An instance of an `EventListener`.  

---

## Return

The `Kuzzle` instance.
