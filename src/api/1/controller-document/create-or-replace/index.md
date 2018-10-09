---
layout: full.html.hbs
algolia: true
title: createOrReplace
---

# createOrReplace

{{{since "1.0.0"}}}

Create a new document in the persistent data storage, or replace its content if it already exists.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/<documentId>[?refresh=wait_for]
Method: PUT  
Body:
```

```js
{
  // Document content
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "createOrReplace",
  "_id": "<documentId>",    
  "body": {
    // document content
  }
}
```

---

## Arguments

* `collection`: data collection
* `documentId`: unique identifier of the document to create or replace
* `index`: data index

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the created/replaced document is indexed

---

## Body properties

New document content.

---

## Response

Return an object with the following properties:

* `_id`: created document unique identifier
* `_source`: document content
* `_version`: version of the created document
* `created`: a boolean telling if a new document has been created

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "createOrReplace",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<documentId>",
    "_source": {
      // document content
    },
    "_version": 1, 
    "created": true
  }
}
```
