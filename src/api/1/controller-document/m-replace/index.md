---
layout: full.html.hbs
algolia: true
title: mReplace
---


# mReplace

{{{since "1.0.0"}}}

Replaces multiple documents.


## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the replacements are indexed


## Response

Returns a `hits` array containing the list of replaced documents.

Each document has the following properties:

* `_id`: document unique identifier
* `_source`: document content
* `_version`: version number of the document

If one or more document cannot be replaced, the response status is set to `206`, and the `error` object contain a [partial error]({{ site_base_path }}api/1/errors/#partialerror) error.

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "action": "mReplace",
  "controller": "document",
  "requestId": "<unique request identifier>",
  "result": {
    "hits": [
      {
        "_id": "<documentId>",
        "_source": {
          // new document content
        },
        "_version": 2
      },
      {
        "_id": "<anotherDocumentId>",
        "_source": {
          // new document content
        },
        "_version": 4
      }
    ],
    "total": 2
  }
}
```
