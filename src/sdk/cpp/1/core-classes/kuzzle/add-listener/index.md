---
code: true
type: page
title: addListener
description: Add a listener to an event
---

# addListener

Adds a listener to an event.
When an event is triggered, listeners are triggered in the order in which they were added.
Theses listener will receive a `const std::string` as argument. This string is a JSON payload representing the event.

## Arguments

```cpp
virtual kuzzleio::KuzzleEventEmitter* addListener(
    kuzzleio::Event event,
    kuzzleio::SharedEventListener listener);
```

<br/>

| Argument   | Type                                     | Description                                                  |
| ---------- | ---------------------------------------- | ------------------------------------------------------------ |
| `event`    | <pre>kuzzleio::Event</pre>               | An enum representing the listened [event](/sdk/cpp/1/essentials/events) |
| `listener` | <pre>kuzzleio::SharedEventListener</pre> | Smart pointer to a c++11 lambda                              |

### event

One of the following event:

```cpp
KUZZLE_EVENT_CONNECTED,
KUZZLE_EVENT_DISCARDED,
KUZZLE_EVENT_DISCONNECTED,
KUZZLE_EVENT_LOGIN_ATTEMPT,
KUZZLE_EVENT_NETWORK_ERROR,
KUZZLE_EVENT_OFFLINE_QUEUE_POP,
KUZZLE_EVENT_OFFLINE_QUEUE_PUSH,
KUZZLE_EVENT_QUERY_ERROR,
KUZZLE_EVENT_RECONNECTED,
KUZZLE_EVENT_JWT_EXPIRED,
KUZZLE_EVENT_ERROR
```

### listener

`EventListener` is defined as `const std::function<void(const std::string)>`

`SharedEventListener` is defined as `std::shared_ptr<EventListener>`

## Return

The `Kuzzle` instance.

## Usage

<<< ./snippets/add-listener.cpp
