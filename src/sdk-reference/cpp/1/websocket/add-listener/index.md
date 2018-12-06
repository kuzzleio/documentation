---
layout: sdk.html.hbs
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

| Argument   | Type                      | Description
| ---------- |------------------------------------------------------------------------------------------------------ | -------- |
| `event`    | <pre>kuzzleio::Event</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/essentials/event-handling)
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

A c++11 lambda which take a `const std::string&`
`EventListener` is defined as `const std::function<void(const std::string&)>`.
