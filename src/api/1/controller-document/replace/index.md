---
layout: full.html.hbs
algolia: true
title: replace
algolia: true
---

# replace

{{{since "1.0.0"}}}

Replaces the content of an existing document.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/<documentId>/_replace[?refresh=wait_for]
Method: PUT  
Body:
```

```js
{
  // new document content
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "replace",
  "body": {
    // new document content
  }
}
```

---

## Arguments

* `collection`: data collection
* `documentId`: unique ID of the document to replace
* `index`: data index

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the new document content is indexed

---

## Body properties

New document content.

---

## Response

Returns an object containing updated document information, with the following properties:

* `_id`: document unique identifier
* `_source`: new document content
* `_version`: updated document version number

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "replace",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<documentId>",
    "_source": { 
      // new document content
    },
    "_version": 13
  }
}
```
