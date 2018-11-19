---
layout: full.html.hbs
algolia: true
title: Testing Nested Fields
description: How to test nested fields
order: 200
---

## Testing Nested Fields

Examples described in this documentation show how to test for fields at the root of the provided data objects, but it is also possible to add filters on nested properties.

To do that, instead of giving the name of the property to test, its path must be supplied as follows: `path.to.property`

### Example

Given the following document:

```json
{
    "name": {
        "first": "Grace",
        "last": "Hopper"
    }
}
```

Here is a filter, testing for equality on the field `last` in the `name` object:

```json
{
    "equals": {
        "name.last": "Hopper"
    }
}
```
