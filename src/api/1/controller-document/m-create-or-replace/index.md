---
layout: full.html.hbs
algolia: true
title: mCreateOrReplace
---


# mCreateOrReplace

{{{since "1.0.0"}}}

Creates or replaces multiple documents.


## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the created/replaced documents are indexed


## Response

Returns a `hits` array, containing the list of created documents, in the same order than the one provided in the query.

Each created document is an object with the following properties:

* `_id`: created document unique identifier
* `_source`: document content
* `_version`: version number of the document 
* `created`: a boolean telling whether a document is created 

If one or more document creations fail, the response status is set to `206`, and the `error` object contain a [partial error]({{ site_base_path }}api/1/errors/#partialerror) error.

### Example

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "action": "mCreateOrReplace",
  "controller": "document",
  "requestId": "<unique request identifier>",
  "result": {
    "hits": [
      {
        "_id": "<documentId>",
        "_source": {
          // document content
        },
        "_version": 2,
        "created": false
      },
      {
        "_id": "<anotherDocumentId>",
        "_source": {
          // document content
        },
        "_version": 1,
        "created": true
      }
    ],
    "total": 2
  }
}
```
