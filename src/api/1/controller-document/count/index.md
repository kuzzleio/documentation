---
layout: full.html.hbs
algolia: true
title: count
---


# count

{{{since "1.0.0"}}}

Counts documents in a data collection.

A query can be provided to alter the count result, otherwise returns the total number of documents in the data collection.


## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `includeTrash`: include documents from the [trashcan]({{ site_base_path }}guide/1/essentials/document-metadata/)


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
