---
layout: sdk.html.hbs
algolia: true
title: validateSpecifications
description: Validate specifications format
---

# validateSpecifications

The validateSpecifications method checks if a validation specification is well formatted. It does not store nor modify the existing specification.  

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
validateSpecifications(index, collection, specifications, options = {})
```

## Arguments

| Arguments    | Type    | Description | Required
|--------------|---------|-------------|----------
| ``index`` | String | Index name    | yes  |
| ``collection`` | String | Collection name    | yes  |
| ``specifications`` | Object | Specifications to validate  | yes  |
| ``options`` | Object | An object containing query options    | no  |

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

Resolve to an object which contain informations about the specifications validity.  
It contains the following properties:

| Property    | Type    | Description |
|--------------|---------|-------------|
| ``valid`` | Boolean | Specifications validity   |
| ``details`` | Array.<String> | Specifications errors    |
| ``description`` | String | Global description of errors    |

## Usage

[snippet=validate-specifications]
