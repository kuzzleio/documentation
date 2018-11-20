---
layout: full.html.hbs
algolia: true
title: deleteByQuery
---


# deleteByQuery

{{{since "1.0.0"}}}

Deletes documents matching the provided search query.


## Arguments

* `collection`: data collection
* `index`: data index


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
