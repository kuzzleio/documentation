---
layout: sdk.html.hbs
title: once
description: Attaches a listener to an event and removes it after it has been triggered once
---

# once

Attaches a listener to an event and removes it after it has been triggered once.

## Signature

```cpp
virtual void once(kuzzleio::Event event, kuzzleio::EventListener* listener) = 0;
```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>kuzzleio::Event</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/cpp/1/events)
| `listener` | <pre>kuzzleio::EventListener*</pre> | A pointer to a c++11 lambda

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

### **listener**

A c++11 lambda which takes a `const std::string` argument.
`EventListener` is defined as `const std::function<void(const std::string)>`.