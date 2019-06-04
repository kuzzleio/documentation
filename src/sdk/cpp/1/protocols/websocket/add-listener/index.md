---
code: true
type: page
title: addListener
description: Adds a listener to an event
---

# addListener

Adds a listener to an event.

## Signature

```cpp
void addListener(kuzzleio::Event event, kuzzleio::EventListener* listener);
```

## Arguments

| Argument   | Type                                 | Description                                                  |
| ---------- | ------------------------------------ | ------------------------------------------------------------ |
| `event`    | <pre>kuzzleio::KuzzleEvent</pre>     | An enum representing the listener [event](/sdk/cpp/1/essentials/events) |
| `listener` | <pre>kuzzleio::EventListener\*</pre> | Pointer to a c++11 lambda                                    |

### event

One of the following event:

```cpp
EVENT_CONNECTED,
EVENT_DISCARDED,
EVENT_DISCONNECTED,
EVENT_LOGIN_ATTEMPT,
EVENT_NETWORK_ERROR,
EVENT_OFFLINE_QUEUE_POP,
EVENT_OFFLINE_QUEUE_PUSH,
EVENT_QUERY_ERROR,
EVENT_RECONNECTED,
EVENT_JWT_EXPIRED,
EVENT_ERROR
```

### listener

A c++11 lambda which takes a `const std::string` argument.
`EventListener` is defined as `const std::function<void(const std::string)>`.
