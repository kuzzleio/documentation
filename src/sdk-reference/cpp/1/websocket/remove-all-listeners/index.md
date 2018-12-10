---
layout: sdk.html.hbs
title: removeAllListeners
description: Remove a listener to an event
---

# removeAllListeners

Removes all listeners of an Event type.

## Signature

```cpp
void removeAllListeners(kuzzleio::Event event);
```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>kuzzleio::Event</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/cpp/1/events/)

### **event**

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
