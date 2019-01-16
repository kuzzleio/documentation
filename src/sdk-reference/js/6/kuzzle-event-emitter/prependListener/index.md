---
layout: sdk.html.hbs
title: prependListener
description: Prepends a new listener for an event
---

# prependListener

Adds a listener function to the beginning of the listeners array for an event.

## Signature

```js
prependListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | The name of the event |
| `callback` | <pre>function</pre> | Function to call every time the event is triggered     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=prepend-listener]
