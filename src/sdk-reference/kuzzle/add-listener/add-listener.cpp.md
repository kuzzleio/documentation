```cpp
kuzzleio::KuzzleEventEmitter* addListener(Event event, kuzzleio::EventListener* listener)
```

## Arguments

| Argument | Type | Description | Required |
|--------|------|-------------|------------ |
| `event` | Event | An enum representing the event to listening to  | yes |
| `listener` | EventListener* | A pointer to an instance of an `EventListener` | yes |

### event

One of the following event:
```cpp
CONNECTED
DISCARDED
DISCONNECTED
LOGIN_ATTEMPT
NETWORK_ERROR
OFFLINE_QUEUE_POP
OFFLINE_QUEUE_PUSH
QUERY_ERROR
RECONNECTED
JWT_EXPIRED
ERROR
```

### listener

An instance of a class that inherits from `kuzzleio::EventListener`.  
This class must implement the following method:
```cpp
class MyListener : public kuzzleio::EventListener {
  public:
    void trigger(char *json_payload) const {
      // Do something with the json_payload
    }
};
```

## Return

The `Kuzzle` instance.

## Usage

[code-example=add-listener]
