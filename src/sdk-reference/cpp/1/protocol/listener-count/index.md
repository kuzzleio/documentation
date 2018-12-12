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
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>kuzzleio::Event</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/cpp/1/events)

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

## Return

The number of listeners attached to the specified event.
