---
layout: sdk.html.hbs
algolia: true
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Delete the validation specifications associated with the `<collection>`.  

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} [options]
 * @returns {Promise<boolean>}
 */
deleteSpecifications (index, collection, options = null)
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

True if success.

## Usage

[snippet=delete-specifications]
