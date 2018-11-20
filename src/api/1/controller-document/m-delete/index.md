---
layout: full.html.hbs
algolia: true
title: mDelete
---


# mDelete

{{{since "1.0.0"}}}

Deletes multiple documents.


## Arguments

* `collection`: data collection
* `index`: data index

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the deletions are indexed


## Response

Returns an array with the list of successfully deleted document identifiers.

If one or more document deletions fail, the response status is set to `206`, and the `error` object contain a [partial error]({{ site_base_path }}api/1/errors/#partialerror) error.

```js
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "action": "mDelete",
  "controller": "document",
  "requestId": "<unique request identifier>",
  "result": [
    "<documentId>",
    "<anotherDocumentId>"
  ]
}
```
