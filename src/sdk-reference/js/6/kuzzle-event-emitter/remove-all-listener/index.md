---
layout: sdk.html.hbs
title: removeAllListeners
description: Adds a new listener for an event
---

# removeAllListeners

Removes all listeners to an event.  
If no eventName is specified, removes all the listeners for all events.

## Signature

```js
removeAllListeners ([eventName]);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=remove-all-listeners]
