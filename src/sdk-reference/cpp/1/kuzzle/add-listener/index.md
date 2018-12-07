---
layout: sdk.html.hbs
title: addListener
description: Add a listener to an event
---

# addListener

Adds a listener to an event.
When an event is triggered, listeners are triggered in the order in which they were added.
Theses listener will receive a `const std::string` as argument. This string is a JSON payload representing the event.

## Signature

```cpp
kuzzleio::KuzzleEventEmitter* addListener(kuzzleio::KuzzleEvent event, kuzzleio::EventListener* listener)
```

## Arguments

| Argument   | Type                      | Description                                                                                            | Required |
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------ | -------- |
| `event`    | kuzzleio::KuzzleEvent           | An enum representing the listened [event]({{ site_base_path }}sdk-reference/essentials/event-handling) | yes      |
| `listener` | kuzzleio::EventListener\* | A pointer to a c++11 lambda                                           | yes      |

### **event**

One of the following event:

```cpp
KUZZLE_EVENT_CONNECTED
KUZZLE_EVENT_DISCARDED
KUZZLE_EVENT_DISCONNECTED
KUZZLE_EVENT_LOGIN_ATTEMPT
KUZZLE_EVENT_NETWORK_ERROR
KUZZLE_EVENT_OFFLINE_QUEUE_POP
KUZZLE_EVENT_OFFLINE_QUEUE_PUSH
KUZZLE_EVENT_QUERY_ERROR
KUZZLE_EVENT_RECONNECTED
KUZZLE_EVENT_JWT_EXPIRED
KUZZLE_EVENT_ERROR
```

### **listener**

A c++11 lambda which take a `const std::string`
`EventListener` is defined as `const std::function<void(const std::string)>`.

## Return

The `Kuzzle` instance.

## Usage

[snippet=add-listener]
