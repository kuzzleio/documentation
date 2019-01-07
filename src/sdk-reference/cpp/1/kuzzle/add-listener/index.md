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
virtual kuzzleio::Kuzzlekuzzleio::EventEmitter* addListener(
    kuzzleio::Event event, 
    kuzzleio::EventListener* listener);
```

## Arguments

| Argument   | Type                      | Description            |
| ---------- | ------------------------- | ------------------------|
| `event`    | kuzzleio::Event           | An enum representing the listened [event]({{ site_base_path }}sdk-reference/cpp/1/events) |
| `listener` | kuzzleio::EventListener\* | Pointer to a c++11 lambda   |

### event

One of the following event:

```cpp
CONNECTED,
DISCARDED,
DISCONNECTED,
LOGIN_ATTEMPT,
NETWORK_ERROR,
OFFLINE_QUEUE_POP,
OFFLINE_QUEUE_PUSH,
QUERY_ERROR,
RECONNECTED,
JWT_EXPIRED,
ERROR
```

### listener

A c++11 lambda which take a `const std::string`
`EventListener` is defined as `const std::function<void(const std::string)>`.

## Return

The `Kuzzle` instance.

## Usage

[snippet=add-listener]
