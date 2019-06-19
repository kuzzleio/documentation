---
code: true
type: page
title: setContent
description: Document:setContent
---

# setContent

Replaces the current content with new data.  
This is a helper function returning a reference to itself so that you can easily chain calls.

:::info
Changes made by this function won't be applied until the `save` method is called
:::

---

## setContent(data, [replace])

| Arguments | Type        | Description                                                               |
| --------- | ----------- | ------------------------------------------------------------------------- |
| `data`    | JSON Object | New content                                                               |
| `replace` | boolean     | true: replace the current content with the provided data, false: merge it |

**Note:** by default, the `replace` argument is set to `false`

---

## Return Value

Returns this `Document` object to allow chaining.

## Usage

<<< ./snippets/set-content-1.php
