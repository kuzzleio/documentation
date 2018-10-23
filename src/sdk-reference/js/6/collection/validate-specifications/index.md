---
layout: sdk.html.hbs
algolia: true
title: validateSpecifications
description: Validate specifications format
order: 200
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
 * @returns {Promise<object>}
 */
validateSpecifications (index, collection, specifications, options = null)
```

## Arguments

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | string | Index name    |
| ``collection`` | string | Collection name    |
| ``specifications`` | object | Specifications to validate  |
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

Resolve to an object which contain informations about the specifications validity.  
It contains the following properties:

| Property    | Type    | Description |
|--------------|---------|-------------|
| ``valid`` | boolean | Specifications validity   |
| ``details`` | array&lt;string&gt; | Specifications errors    |
| ``description`` | string | Global description of errors    |

## Usage

[snippet=validate-specifications]
