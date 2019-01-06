---
layout: sdk.html.hbs
title: removeAllListeners
description: Removes all listeners from an event
---

# removeAllListeners

Removes all listeners from an event.

## Signature

```csharp
public virtual void removeAllListeners(SWIGTYPE_p_Event arg0);

```

## Arguments

| Argument   | Type                      | Description
| ---------- | ------------------------- | ---------------------------
| `event`    | <pre>kuzzleio::Event</pre>  | Enum representing the event to emit
| `listener` | <pre>kuzzleio::EventListener\*</pre> | Pointer to a c++11 lambda

