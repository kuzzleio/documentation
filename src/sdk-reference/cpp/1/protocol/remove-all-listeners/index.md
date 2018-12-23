---
layout: sdk.html.hbs
title: removeAllListeners
description: Removes all listeners from an event
---

# removeAllListeners

Removes all listeners from an event.

## Arguments

```cpp
virtual void removeAllListeners(kuzzleio::Event event) = 0;
```

| Argument   | Type                      | Description
| ---------- | ------------------------- | ---------------------------
| `event`    | <pre>kuzzleio::Event</pre>  | Enum representing the event to emit
| `listener` | <pre>kuzzleio::EventListener\*</pre> | Pointer to a c++11 lambda

### event

One of the following [event]({{ site_base_path }}sdk-reference/cpp/1/events):

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
