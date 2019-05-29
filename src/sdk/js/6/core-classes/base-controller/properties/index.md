---
code: true
type: page
title: Properties
description: BaseController Properties
---

# Properties

| Property name        | Type     | Description          |
| -------------------- | -------- | --------------------------------------- |
| `name`               | <pre>string</pre> | Controller name    |
| `kuzzle`             | <a href="/sdk/js/6/core-classes/kuzzle"><pre>Kuzzle</pre></a> | Kuzzle SDK instance      |

**Note:**
 - The `name` property will be injected in the request sent by the [BaseController.query](/sdk/js/6/core-classes/base-controller/query) method if the `controller` property is not set.