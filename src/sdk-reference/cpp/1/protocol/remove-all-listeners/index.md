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
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>kuzzleio::Event</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/cpp/1/events/)

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
