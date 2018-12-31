---
layout: sdk.html.hbs
title: addListener
description: Add a listener to an event
---

# addListener

Adds a listener to an event.
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```go
AddListener(event int, channel chan<- json.RawMessage{})
```

## Arguments

| Argument  | Type    | Description                                                                                                                                   | Required |
| --------- | ------- | --------------------------------------------------------------------------------------------------------------------------------------------- | -------- |
| `event`   | int     | One of the event described in the [Event Handling]({{ site_base_path }}sdk-reference/go/1/essentials/event-handling) section of this documentation | yes      |
| `channel` | channel | A channel taking a json.RawMessage                                                                                                                 | yes      |

### **event**

You can register one of the following events:

```go
event.Connected
event.Discarded
event.Disconnected
event.LoginAttempt
event.NetworkError
event.OfflineQueuePop
event.OfflineQueuePush
event.QueryError
event.Reconnected
event.TokenExpired
event.Error
event.Done
```

### **channel**

The channel will receive an interface with the event data each time the registered event is triggered.

## Usage

[snippet=add-listener]
