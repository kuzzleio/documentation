---
layout: sdk.html.hbs
title: once
description: Attaches a listener to an event and removes it after it has been triggered once
---

# once

Attaches a listener to an event and removes it after it has been triggered once.

## Signature

```cpp
void once(kuzzleio::Event event, kuzzleio::EventListener* listener);
```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>kuzzleio::KuzzleEvent</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/cpp/1/events/)
| `listener` | <pre>kuzzleio::EventListener*</pre> | A pointer to a c++11 lambda

### **event**

One of the following event:

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

### **listener**

A c++11 lambda which take a `const std::string&`
`EventListener` is defined as `const std::function<void(const std::string&)>`.
