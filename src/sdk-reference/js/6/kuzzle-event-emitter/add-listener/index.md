---
layout: sdk.html.hbs
title: addListener
description: Returns addListener for an event
---

# addListener

Adds a listener to an event.
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```js
addListener (eventName);
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `event`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6//events) section of this documentation |
| `callback` | <pre>function</pre> | Function to call every time the event is triggered     |

## Return

The `Kuzzle` instance.
