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
void emitEvent(kuzzleio::Event event);
```

## Arguments

| Argument | Type                       | Description                                                  |
| -------- | -------------------------- | ------------------------------------------------------------ |
| `event`  | <pre>kuzzleio::Event</pre> | An enum representing the listener [event](/sdk/cpp/1/essentials/events) |

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
