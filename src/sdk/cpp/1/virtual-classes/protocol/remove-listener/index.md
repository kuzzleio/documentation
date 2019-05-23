---
code: true
type: page
title: removeListener
description: Removes a listener from an event
---

# removeListener

Removes a listener from an event.

## Signature

```cpp
virtual void removeListener(kuzzleio::Event event, kuzzleio::EventListener* listener) = 0;
```

## Arguments

| Argument   | Type                                 | Description                         |
| ---------- | ------------------------------------ | ----------------------------------- |
| `event`    | <pre>kuzzleio::Event</pre>           | Enum representing the event to emit |
| `listener` | <pre>kuzzleio::EventListener\*</pre> | Pointer to a c++11 lambda           |

### event

One of the following [event](/sdk/cpp/1/events):

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
