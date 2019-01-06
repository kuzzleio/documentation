---
layout: sdk.html.hbs
title: emitEvent
description: Emits an event
---

# emitEvent

Emits an event.

## Signature

```csharp
public virtual void emitEvent(SWIGTYPE_p_Event arg0);

```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | -----------
| `event`    | <pre>Kuzzleio::Event</pre>  | Enum representing the event to emit

### event

One of the following [event]({{ site_base_path }}sdk-reference/csharp/1/events):

```csharp
EVENT_CONNECTED,
EVENT_DISCARDED,
EVENT_DISCONNECTED,
EVENT_LOGIN_ATTEMPT,
EVENT_NETWORK_ERROR,
EVENT_OFFLINE_QUEUE_POP,
EVENT_OFFLINE_QUEUE_PUSH,
EVENT_QUERY_ERROR,
EVENT_RECONNECTED,
EVENT_JWT_EXPIRED,
EVENT_ERROR
```
