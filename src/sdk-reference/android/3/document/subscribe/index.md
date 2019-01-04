---
layout: sdk.html.hbs
title: subscribe
description: Document:subscribe
---
  

# subscribe
Listens to changes occuring in this document.
Throws an error if this document has not yet been created in Kuzzle.

The provided callback will be called everytime a [notification]({{ site_base_path }}sdk-reference/android/3/notifications) is received from Kuzzle.

---

## subscribe([options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``options`` | object | Subscription configuration |
| ``callback`` | function | Callback that will be called each time a change has been detected on this document |

---

## Options

Options are directly passed to the [Room]({{ site_base_path }}sdk-reference/android/3/room/) object constructor.

---

## Return Value

Returns an object exposing the following method:  
  `onDone(callback)`

The `callback` argument is called when the subscription ends, either successfully or with an error.

## Usage

[snippet=subscribe-1]
