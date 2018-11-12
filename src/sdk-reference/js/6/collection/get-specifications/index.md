---
layout: sdk.html.hbs
algolia: true
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the `<collection>`.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} [options]
* @returns {Promise<object>}
 */
getSpecifications (index, collection, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|----------
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``options`` | object | Query options    |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve to an object representing the collection specifications.

## Usage

[snippet=get-specifications]
