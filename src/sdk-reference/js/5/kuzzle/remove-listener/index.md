---
layout: sdk.html.hbs
algolia: true
title: removeListener
description: Kuzzle:removeListener
algolia: true
---
  

# removeListener
Remove a listener from an event.

---

## removeListener(event, callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``event`` | string | One of the events described in the ``Event Handling`` section of this documentation |
| ``callback`` | function/object | the callback |

---

## Return Value

Returns the `Kuzzle` object to allow chaining.

## Usage

[snippet=remove-listener-1]
