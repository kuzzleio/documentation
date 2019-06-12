---
code: true
type: page
title: emitEvent
description: Emits an event
---

# emitEvent

Emits an event.

## Signature

```cpp
virtual void emitEvent(kuzzleio::Event event) = 0;
```

## Arguments

| Argument | Type                       | Description                         |
| -------- | -------------------------- | ----------------------------------- |
| `event`  | <pre>kuzzleio::Event</pre> | Enum representing the event to emit |

### event

One of the following [event](/sdk/cpp/1/essentials/events):

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
