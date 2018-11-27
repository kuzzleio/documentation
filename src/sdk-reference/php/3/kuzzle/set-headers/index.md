---
layout: sdk.html.hbs
algolia: true
title: setHeaders
description: Kuzzle:setHeaders
algolia: true
---
  

# setHeaders
This is a helper function returning itself, allowing to easily chain calls.

---

## setHeaders(content, [replace])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``content`` | JSON Object | New content |
| ``replace`` | boolean | true: replace the current content with the provided data, false: merge it |

**Note:** by default, the ``replace`` argument is set to ``false``

---

## Return value

Returns the `Kuzzle` object to allow chaining.

## Usage

[snippet=set-headers-1]
