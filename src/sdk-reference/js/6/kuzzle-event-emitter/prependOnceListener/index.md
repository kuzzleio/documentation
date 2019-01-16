---
layout: sdk.html.hbs
title: prependOnceListener
description: Prepends a new once listener for an event
---

# prependOnceListener

Adds a **one-time** listener function for an event to the beginning of the listeners array.  
The next time that event is triggered, this listener is removed, and then invoked.

## Signature

```js
prependOnceListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | The name of the event |
| `callback` | <pre>function</pre> | Function to call when the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=prepend-once-listener]
