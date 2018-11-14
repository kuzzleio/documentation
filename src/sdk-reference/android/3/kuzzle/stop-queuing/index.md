---
layout: sdk.html.hbs
algolia: true
title: stopQueuing
description: Kuzzle:stopQueuing
---
  

# stopQueuing
[snippet=stop-queuing-1]
Stops the requests queuing. Works only in offline mode, and if the [autoQueue]({{ site_base_path }}sdk-reference/kuzzle/#properties) option is set to `false`.

---

## Return Value

Returns the `Kuzzle` SDK object to allow chaining.
