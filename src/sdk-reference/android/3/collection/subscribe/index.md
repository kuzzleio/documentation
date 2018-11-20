---
layout: sdk.html.hbs
algolia: true
title: subscribe
description: Collection:subscribe
---

  

# subscribe
Subscribes to this data collection with a set of filters.

The provided callback will be called everytime a [notification]({{ site_base_path }}sdk-reference/essentials/notifications) is received from Kuzzle.


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns an object exposing the following method:  
  `onDone(callback)`

The `callback` argument is called when the subscription ends, either successfully or with an error.

## Usage

[snippet=subscribe-1]
