---
layout: full.html.hbs
algolia: true
title: delete
---


# delete

{{{since "1.0.0"}}}

Deletes a document.


## Arguments

* `collection`: data collection
* `documentId`: document unique identifier
* `index`: data index

### Optional:

* `refresh`: if set to `wait_for`, Kuzzle will not respond until the deletion has been indexed

---

## Response

Returns an `_id` property with the deleted document unique ID.

```javascript
{
  "status": 200,
  "error": null,
  "index": "<index>",
  "collection": "<collection>",
  "controller": "document",
  "action": "delete",
  "requestId": "<unique request identifier>",
  "result": {
    "_id": "<documentId>"
  }
}
```
