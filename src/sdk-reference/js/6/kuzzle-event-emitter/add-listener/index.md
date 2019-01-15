---
layout: sdk.html.hbs
title: addListener
description: Adds a new listener for an event
---

# addListener

Adds a listener to an event.
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```js
addListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `callback` | <pre>function</pre> | Function to call every time the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=add-listener]
