---
layout: sdk.html.hbs
title: addOnceListener
description: Adds a new listener for an event
---

# addOnceListener

Adds an once listener to an event.  
The listener will be called once and then removed.  
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```js
addOnceListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `callback` | <pre>function</pre> | Function to call when the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=add-once-listener]
