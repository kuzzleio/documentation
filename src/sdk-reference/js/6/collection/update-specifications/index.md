---
layout: sdk.html.hbs
algolia: true
title: updateSpecifications
description: Update the validation specifications
order: 200
---

# updateSpecifications

The updateSpecifications method allows you to create or update the validation specifications for one or more index/collection pairs.

When the validation specification is not formatted correctly, a detailed error message is returned to help you to debug.

## Signature

```javascript
/**
* @param {string} index
* @param {string} collection
* @param {object} specifications
* @param {object} [options]
* @returns {Promise.<object>}
 */
updateSpecifications(index, collection, specifications, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``specifications`` | Object | Specifications to update  | yes  |
| ``options`` | Object | Query options    | no  |

### **specifications**

An object representing the specifications.  

This object must follow the [Specification Structure]({{ site_base_path }}validation-reference/schema):

```js
{
  myindex: {
    mycollection: {
      strict: <true|false>,
      fields: {
        // ... specification for each field
      }
    }
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
