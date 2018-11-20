---
layout: full.html.hbs
algolia: true
title: import
---


# import

{{{since "1.0.0"}}}

Creates, updates or deletes large amounts of documents as fast as possible.

This route is faster than the `document:m*` routes family (e.g. [document:mCreate]({{ site_base_path }}api/1/controller-document/m-create)), but no real-time notifications will be generated, even if some of the documents in the import match subscription filters.

If some documents actions fail, the client will receive a [PartialError]({{ site_base_path }}api/1/documentation/errors/#partialerror) error.


## Arguments

* `collection`: data collection
* `index`: data index


## Response

Returns a `items` array containing the list of executed queries result, in the same order than in the query.

Each query result contains the following properties:

* `_id`: document unique identifier
* `status`: HTTP status code for that query

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "bulk",
  "action": "import",
  "requestId": "<unique request identifier>",
  "result": {
    "items": [
      {
        "create": {
          "_id": "<documentId>",
          "status": 200
        }
      },
      {
        "create": {
          "_id": "<documentId>",
          "status": 200
        }
      },
      {
        "create": {
          "_id": "<documentId>",
          "status": 200
        }
      }
    ]
  }
}
```
