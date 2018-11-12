---
layout: sdk.html.hbs
algolia: true
title: truncate
description: Remove all documents from collection
---

# truncate

Remove all documents from a collection while keeping the associated mapping.  
It is faster than deleting all documents from a collection.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} [options]
* @returns {Promise.<object>}
 */
truncate(index, collection, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``options`` | Object | Query options    | no  |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve is collection is successfully truncated.

## Usage

[snippet=truncate]
