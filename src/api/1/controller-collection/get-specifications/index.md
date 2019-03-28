---
layout: full.html.hbs
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

* `collection`: collection name
* `index`: index name

---

## Response

Returns a specifications object with the following properties:

* `collection`: specified collection
* `index`: specified index
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

- [Common errors]({{ site_base_path }}api/1/essentials/errors/#common-errors)
- [NotFoundError]({{ site_base_path }}api/1/essentials/errors/#notfounderror)
