---
layout: sdk.html.hbs
algolia: true
title: addListener
description: Add a listener to an event
order: 200
---

# addListener

Adds a listener to an event.  
When an event is triggered, listeners are triggered in the order in which they were added.  
Theses listener will receive a `const std::string` as argument. This string is a JSON payload representing the event.

## Signature

```cpp
kuzzleio::KuzzleEventEmitter* addListener(kuzzleio::Event event, EventListener* listener)
```

## Arguments

| Argument   | Type                      | Description                                                                                            | Required |
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------ | -------- |
| `event`    | Event                     | An enum representing the listened [event]({{ site_base_path }}sdk-reference/essentials/event-handling) | yes      |
| `listener` | EventListener\* | A pointer to a c++11 lambda                                           | yes      |

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

A c++11 lambda which take a `const std::string`
`EventListener` is defined as `const std::function<void(const std::string)>`.

## Return

The `Kuzzle` instance.

## Usage

[snippet=add-listener]
