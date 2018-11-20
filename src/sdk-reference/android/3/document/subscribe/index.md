---
layout: sdk.html.hbs
algolia: true
title: subscribe
description: Document:subscribe
---

  

# subscribe
Listens to changes occuring in this document.
Throws an error if this document has not yet been created in Kuzzle.

The provided callback will be called everytime a [notification]({{ site_base_path }}sdk-reference/essentials/notifications) is received from Kuzzle.


## Options

Options are directly passed to the [Room]({{ site_base_path }}sdk-reference/room/) object constructor.

---

## Return Value

Returns an object exposing the following method:  
  `onDone(callback)`

The `callback` argument is called when the subscription ends, either successfully or with an error.

## Usage

[snippet=subscribe-1]
