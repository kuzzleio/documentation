---
layout: full.html.hbs
algolia: true
title: getSpecifications
---

# getSpecifications

{{{since "1.0.0"}}}

Returns the validation specifications associated to the given index and collection.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_specifications
Method: GET
```

### Other protocols


```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "collection",
  "action": "getSpecifications"
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Response

Returns a specifications object with the following properties:

* `collection`: specified data collection
* `index`: specified data index
* `validation`: specifications description

```js
{
  "status": 200,
  "error": null,
  "action": "getSpecifications",
  "controller": "collection",
  "collection": "<collection>",
  "index": "<index>",
  "result": {
    "collection": "<collection>",
    "index": "<index>",
    "validation": {
      "fields": {
        "myField": {
          "defaultValue": 42,
          "mandatory": true,
          "type": "integer"
        }
      },
      "strict": true
    }
  }
}
```

---

## Possible errors

- [Common errors]({{ site_base_path }}api/1/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/errors/#notfounderror)
