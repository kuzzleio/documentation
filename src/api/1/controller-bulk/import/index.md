---
layout: full.html.hbs
algolia: true
title: import
---

# import

{{{since "1.0.0"}}}

A bulk import allows your application to perform multiple write operations in a single request.

This is especially useful if you want to create a large number of documents. A bulk import request will execute faster than calls to [document:mCreate]({{ site_base_path }}api/1/controller-document/m-create)

Bulk imports do not emit document events in Kuzzle, meaning that you <strong>won't receive any real-time notfications</strong> on your document subcriptions
even if some of the documents in the import match your subscription filters.

If a subset of the documents fail to save, the client will receive a [PartialError]({{ site_base_path }}api/1/documentation/errors/#partialerror) error.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_bulk
Method: POST  
Body:
```

```js
{
  "bulkData": [
    {"create": {}},
    {"a": "document", "with": "any", "number": "of fields"},
    {"create": {}},
    {"another": "document"},
    {"create": {}},
    {"and": {"another": "one"}}
  ]
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "bulk",
  "action": "import",

  "body": {
    "bulkData": [
      {"create": {}},
      {"a": "document", "with": "any", "number": "of fields"},
      {"create": {}},
      {"another": "document"},
      {"create": {}},
      {"and": {"another": "one"}}
    ]
  }
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Body properties

The body must contain a `bulkData` array, detailing the bulk operations to perform, following [ElasticSearch Bulk API](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/docs-bulk.html).

---

## Response

Return a `hits` array containing the list of executed queries result, in the same order than in the query.

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
    "hits": [
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

