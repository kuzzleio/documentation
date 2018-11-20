---
layout: sdk.html.hbs
algolia: true
title: constructor
description: Room:constructor
order: 1
---

  

# Constructors
The `Room` object is the result of a subscription request, allowing you to manipulate the subscription itself.


## Options

| Option | Type | Description | Default |
|
## Properties

| Property name | Type | Description | get/set |
|--------------|--------|-----------------------------------|---------|
| ``collection`` | string | The subscribed data collection | get |
| ``filters`` | JSON object | The current set of filters of this room | get/set |
| ``headers`` | JSON Object | Common headers for all sent documents. | get/set |
| ``volatile`` | JSON Object | Additional information passed to notifications to other users | get/set |
| ``subscribeToSelf`` | boolean | (Don't) subscribe to notifications fired as a consequence of our own queries | get/set |
| ``roomId`` | string | Unique room identifier | get |

**Notes:**

* the ``headers`` property is inherited from the provided ``Collection`` object and can be overridden
* updating the ``volatile`` property takes effect only after the subscription is renewed
* by default, the global ``volatile`` properties are sent along with the subscription request. If a ``volatile`` option is provided during subscription, it will be merged with the global ``volatile`` for the subscription only. In case of conflicts, subscription ``volatile`` data takes priority over the global ``volatile`` ones.

## Usage

[snippet=constructor-1]
