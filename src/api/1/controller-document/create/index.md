---
layout: full.html.hbs
algolia: true
title: create
---


# create

{{{since "1.0.0"}}}

Creates a new document in the persistent data storage.

Returns an error if the document already exists.


## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `documentId`: set the document unique ID to the provided value, instead of auto-generating a random ID
* `refresh`: if set to `wait_for`, Kuzzle will not respond until the newly created document is indexed


## Response

Returns an object with the following properties:

* `_id`: created document unique identifier
* `_source`: document content
* `_version`: version of the created document (should be `1`)

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "create",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<documentId>",
    "_version": 1,
    "_source": {
      // ...
    },
  }
}
```
