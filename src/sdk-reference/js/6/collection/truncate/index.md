---
layout: sdk.html.hbs
algolia: true
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a `<collection>` while keeping the associated mapping.  

It is faster than deleting all documents from a collection.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} [options]
* @returns {Promise<>}
 */
truncate (index, collection, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``options`` | object | Query options    |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve if the collection is successfully truncated.

## Usage

[snippet=truncate]
