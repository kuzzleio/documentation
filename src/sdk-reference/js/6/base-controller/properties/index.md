---
layout: sdk.html.hbs
title: Properties
description: BaseController Properties
---

# Properties

| Property name        | Type     | Description          |
| -------------------- | -------- | --------------------------------------- |
| `name`               | <pre>string</pre> | Controller name    |
| `kuzzle`             | <a href="{{ site_base_path }}sdk-reference/js/6/kuzzle"><pre>Kuzzle</pre></a> | Kuzzle SDK instance      |

**Note:**
 - The `name` property will be injected in the request sent by the [BaseController.query]({{ site_base_path }}sdk-reference/js/6/base-controller/query) method if the `controller` property is not set.
