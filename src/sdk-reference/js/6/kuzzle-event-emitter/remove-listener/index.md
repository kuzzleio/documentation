---
layout: sdk.html.hbs
title: removeListener
description: Removes a listener function from an event
---

# removeListener

Removes a listener function from an event.

## Signature

```js
removeListener (eventName, callback);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `eventName`    | <pre>string</pre> | The name of the event |
| `callback` | <pre>function</pre> | Callback to remove     |

## Return

The `KuzzleEventEmitter` instance.

## Usage

[snippet=remove-listener]
