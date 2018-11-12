---
layout: sdk.html.hbs
algolia: true
title: updateSpecifications
description: Update the validation specifications
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for a `<collection>`.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} specifications
* @param {object} [options]
* @returns {Promise<object>}
 */
updateSpecifications(index, collection, specifications, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|----------
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``specifications`` | object | Specifications to update  |
| ``options`` | object | Query options    |

### **specifications**

An object representing the specifications.  

This object must follow the [Specification Structure]({{ site_base_path }}validation-reference/schema):

```js
{
  strict: <boolean>,
  fields: {
    // ... specification for each field
  }
}
```

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve to an object containing the specifications.

## Usage

[snippet=update-specifications]
