---
layout: sdk.html.hbs
title: addOnceListener
description: Adds a new listener for an event
---

# addOnceListener

Adds a **one-time** listener function to an event.  
The next time the event is triggered, this listener is removed and then invoked.  
Whenever an event is triggered, listener functions are called in the order they were registered.

## Signature

```js
addOnceListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | The name of the event |
| `callback` | <pre>function</pre> | Function to call when the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=add-once-listener]
