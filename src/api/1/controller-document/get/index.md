---
layout: full.html.hbs
title: get
---

# get

{{{since "1.0.0"}}}

Gets a document.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/<documentId>[?includeTrash=<boolean>]
Method: GET
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "get",
  "_id": "<documentId>",
  "includeTrash": false
}
```

---

## Arguments

* `collection`: data collection
* `documentId`: document unique identifier
* `index`: data index

### Optional:

* `includeTrash`: if true, documents in the [trashcan]({{ site_base_path }}guide/1/essentials/document-metadata/) can be returned

---

## Response

Returns an object with the following properties:

* `_id`: document unique identifier
* `_source`: document content
* `_version`: version of the created document (should be `1`)


```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "get",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<documentId>",
    "_version": 1,
    "_source": {
      "name": {
        "first": "Steve",
        "last": "Wozniak"
      },
      "hobby": "Segway polo",
      "_kuzzle_info": {
        "author": "Bob",
        "createdAt": 1481816934209,
        "updatedAt": null,
        "updater": null,
        "active": true,
        "deletedAt": null
      }
    }
  }
}
```
