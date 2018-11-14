---
layout: sdk.html.hbs
algolia: true
title: collection
description: Kuzzle:collection
---
  

# collection
[snippet=collection-1]
Instantiates a new [Collection]({{ site_base_path }}sdk-reference/collection) object.

---

## collection(collection, [index])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``collection`` | string | The name of the data collection you want to manipulate |
| ``index`` | string | The name of the index containing the data collection |

If no ``index`` is provided, the factory will take the default index set in the main Kuzzle SDK instance. If no default index has been set, an error is thrown.

The ``index`` argument takes precedence over the default index.

---

## Return Value

Returns a [Collection]({{ site_base_path }}sdk-reference/collection) object.
