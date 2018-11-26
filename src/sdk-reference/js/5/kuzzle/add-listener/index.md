---
layout: sdk.html.hbs
algolia: true
title: addListener
description: Kuzzle:addListener
algolia: true
---
  

# addListener
Adds a listener to an event. When an event is fired, listeners are called in the order that they are added.

---

## addListener(event, listener)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``event`` | string | One of the event described in the ``Event Handling`` section of this documentation |
| ``listener`` | function | The function to call each time one of the registered event is fired |

---

## Return Value

Returns the `Kuzzle` object to allow chaining.

## Usage

[snippet=add-listener-1]
