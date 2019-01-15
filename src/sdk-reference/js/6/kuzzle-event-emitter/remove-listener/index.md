---
layout: sdk.html.hbs
title: removeListener
description: Adds a new listener for an event
---

# removeListener

Removes a listener to an event.

## Signature

```js
removeListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `callback` | <pre>function</pre> | Callback to remove     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=remove-listener]
