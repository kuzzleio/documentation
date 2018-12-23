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
| ---------- | ------------------------- | ----------------------------
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

### listener

A c++11 lambda which takes a `const std::string` argument.
`EventListener` is defined as `const std::function<void(const std::string)>`.
