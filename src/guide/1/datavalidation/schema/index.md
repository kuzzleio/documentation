---
layout: full.html.hbs
algolia: true
title: Specification Structure
description: understanding the validation mechanisms
order: 0
algolia: true
---

# Specification Structure

When a collection is created, its specification is empty. As a result, any document will be valid.

In order to update the specification, you can use the [updateSpecifications]({{ site_base_path }}api/1/controller-collection/update-specifications/) action.

Here is an example of a full specification:

```js
const specification = {
  "myIndex": {
    "myCollection": {
      // If true, the document will be rejected if it attemps to
      // define new fields that have not been defined in the schema.
      "strict": true,

      // All documents will be rejected if they did not match theses fields validators
      "fields": {
        "fieldName": {
          "mandatory": true,
          "type": "string",
          "defaultValue": "a default value",
          "multivalued": {
            "value": true,
            "minCount": 1,
            "maxCount": 5
          },
          "typeOptions": {
            "length": {
              "min": 2,
              "max": 12
            }
          }
        },
        "anotherFieldName": {
          "...": "..."
        },
        "myObjectField": {
          "type": "object",
          "...": "..."
        },
        "myObjectField/mySubField": {
          "...": "..."
        }
      },

      // Define custom conditional fields validators to reject document if they meet filters
      "validators": [
        "..."
      ]
    },
    "...": "..."
  },
  "...": "..."
}
```

Learn how to [create simple field validators]({{ site_base_path }}guide/1/datavalidation/fields/) and [complex validators]({{ site_base_path }}guide/1/datavalidation/validators/).
