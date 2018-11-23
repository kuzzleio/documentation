---
layout: sdk.html.hbs
algolia: true
title: stopQueuing
description: Kuzzle:stopQueuing
algolia: true
---
  

# stopQueuing
Stops the requests queuing. Works only in offline mode, and if the [autoQueue]({{ site_base_path }}sdk-reference/kuzzle/#properties) option is set to `false`.

---

## Return Value

Returns the `Kuzzle` SDK object to allow chaining.

## Usage

[snippet=stop-queuing-1]
