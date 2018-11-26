---
layout: sdk.html.hbs
algolia: true
title: setContent
description: Document:setContent
algolia: true
---
  

# setContent
Replaces the current content with new data.  
This is a helper function returning a reference to itself so that you can easily chain calls.

<aside class="notice">
Changes made by this function won't be applied until the <code>save</code> method is called
</aside>

---

## setContent(data, [replace])

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``data`` | JSON Object | New content |
| ``replace`` | boolean | true: replace the current content with the provided data, false: merge it |

**Note:** by default, the ``replace`` argument is set to ``false``

---

## Return Value

Returns this `Document` object to allow chaining.

## Usage

[snippet=set-content-1]
