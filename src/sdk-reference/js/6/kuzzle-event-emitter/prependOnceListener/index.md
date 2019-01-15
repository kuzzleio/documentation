---
layout: sdk.html.hbs
title: prependOnceListener
description: Prepends a new once listener for an event
---

# prependOnceListener

Prepends an once listener in the listeners for an event.

## Signature

```js
prependOnceListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `callback` | <pre>function</pre> | Function to call when the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=prepend-once-listener]
