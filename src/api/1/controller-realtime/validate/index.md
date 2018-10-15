---
layout: full.html.hbs
algolia: true
title: validate
---

# validate

{{{deprecated "1.5.0"}}}

This API route is deprecated and should not be used. Instead, use [document:validate]({{site_base_path}}api/1/controller-document/validate).

Validates data against existing validation rules. 

Messages are always valid if no validation rules are defined on the provided index and collection.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_validate
Method: POST  
Body:
```

```js
{
  // data to validate
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "realtime",
  "action": "validate",
  "body": {
    // data to validate
  }
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Body properties

Data content to validate against validation rules.

---

## Response

Returns an object with the following properties:

* `errorMessages`: the exhaustive list of violated validation rules. Present only if `valid` is false
* `valid`: a boolean telling whether the provided pass all validation rules

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "realtime",
  "action": "validate",
  "result": {
    "valid": true
  }  
}
```
