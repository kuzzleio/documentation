---
layout: full.html.hbs
algolia: true
title: deleteByQuery
algolia: true
---

# deleteByQuery

{{{since "1.0.0"}}}

Deletes documents matching the provided search query.

---

## Query Syntax

### HTTP

```http
URL: http://kuzzle:7512/<index>/<collection>/_query
Method: DELETE  
Body:
```

```js
{
  "query": {
    // ...
  }
}
```

### Other protocols

```js
{
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "deleteByQuery",
  "body": {
    "query": {
      // ...
    }
  }
}
```

---

## Arguments

* `collection`: data collection
* `index`: data index

---

## Body properties

* `query`: documents matching this search query will be deleted. Uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

---

## Response

Returns a `hits` array containing the list of deleted document identifiers.

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "deleteByQuery",
  "requestId": "<unique request identifier>",
  "result": {
    "hits": [
      "id 1", 
      "id 2", 
      "id ...", 
      "id n"
    ]
  }
}
```
