---
layout: sdk.html.hbs
title: listenerCount
description: Returns the number of listeners attached to an event
---

# listenerCount

Returns the number of listeners attached to an event.

## Arguments

```cpp
virtual int listenerCount(kuzzleio::Event event) = 0;
```

| Argument   | Type                      | Description
| ---------- | ------------------------- | -----------
| `event`    | <pre>kuzzleio::Event</pre>  | Enum representing the event to emit

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

## Return

The number of listeners attached to the specified event.
