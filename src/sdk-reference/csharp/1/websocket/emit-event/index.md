---
layout: sdk.html.hbs
title: emitEvent
description: Emits an event
---

# emitEvent

Emits an event.

## Signature

```csharp
public override void emitEvent(SWIGTYPE_p_Event arg0);

```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ------------------------------------------------------------------------------------------------------
| `event`    | <pre>Kuzzleio::Event</pre>           | An enum representing the listener [event]({{ site_base_path }}sdk-reference/csharp/1/events)

### event

One of the following event:

```csharp
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