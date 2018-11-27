---
layout: sdk.html.hbs
title: addListener
description: Add a listener to an event
---

# addListener

Adds a listener to an event.
When an event is triggered, listeners are triggered in the order in which they were added.

## Arguments

```javascript
addListener (event, callback)
```

<br/>

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `event`    | <pre>string</pre> | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6/essentials/events) section of this documentation |
| `callback` | <pre>function</pre> | The function to call every time the event is triggered     |

## Return

The `Kuzzle` instance.

## Usage

[snippet=add-listener]
