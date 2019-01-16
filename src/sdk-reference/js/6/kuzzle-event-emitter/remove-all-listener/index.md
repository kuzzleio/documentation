---
layout: sdk.html.hbs
title: removeAllListeners
description: Removes all listener functions, or all listener functions from an event
---

# removeAllListeners

Removes all listener functions from an event.  
If no eventName is specified, removes all listener functions from all events.

## Signature

```js
removeAllListeners ([eventName]);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | Optional name of the event |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=remove-all-listeners]
