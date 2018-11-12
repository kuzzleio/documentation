---
layout: sdk.html.hbs
algolia: true
title: addListener
description: Add a listener to an event
---

# addListener

Adds a listener to an `<event>`.  
When an event is triggered, listeners are triggered in the order in which they were added.

## Signature

```javascript
/**
 * @param {string} event
 * @param {Function} callback
 * @returns {Kuzzle} this
 */
addListener (event, callback)
```

## Arguments

| Argument   | Type     | Description      |
| ---------- | -------- | -------- |
| `event`    | string   | One of the event described in the [Events]({{ site_base_path }}sdk-reference/js/6/essentials/events) section of this documentation |
| `callback` | Function | The function to call each time one of the registered event is triggered     |

## Return

The `Kuzzle` instance.

## Usage

[snippet=add-listener]
