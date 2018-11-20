---
layout: full.html.hbs
algolia: true
title: validate
---


# validate

{{{since "1.0.0"}}}

Validates data against existing validation rules. 

Documents are always valid if no validation rules are defined on the provided index and collection.

This request does not store the document.


## Arguments

* `collection`: data collection
* `index`: data index


## Response

Returns the document validation status, with the following properties:

* `errorMessages`: the exhaustive list of violated validation rules. Present only if `valid` is false
* `valid`: a boolean telling whether the provided pass all validation rules

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "validate",
  "result": {
    "valid": true 
  }  
}
```
