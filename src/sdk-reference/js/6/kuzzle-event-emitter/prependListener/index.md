---
layout: sdk.html.hbs
title: prependListener
description: Prepends a new listener for an event
---

# prependListener

Prepends a listener in the listeners for an event.

## Signature

```js
prependListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `callback` | <pre>function</pre> | Function to call every time the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=prepend-listener]
