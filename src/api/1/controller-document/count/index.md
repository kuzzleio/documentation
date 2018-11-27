---
layout: full.html.hbs
title: count
---

# count

{{{since "1.0.0"}}}

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_count[?includeTrash=<boolean>]
Method: POST  
Body:
```

```js
{
  "query": {
        "match_all": {}
    }
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "count",
  "body": {
    "query": {
        "match_all": {}
    }
  },
  "includeTrash": false
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `includeTrash`: include documents from the [trashcan]({{ site_base_path }}guide/1/essentials/document-metadata/)

---

## Body properties

### Optional:

* `query`: if provided, will count only documents matching this search query. Uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

---

## Response

Returns an object with the `count` property, an integer showing the number of documents matching the provided search query:

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "count",
  "requestId": "<unique request identifier>",
  "result": {
    "count": 42
  }
}
```
