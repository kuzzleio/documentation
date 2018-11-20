---
layout: sdk.html.hbs
algolia: true
title: truncate
description: Collection:truncate
---

  

# truncate
Truncate the data collection, removing all stored documents but keeping all associated mappings.

This method is a lot faster than removing all documents using multiple delete requests.


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.

## Usage

[snippet=truncate-1]
> Callback response:

```json
{
  "status": 200,
  "error": null,
  "requestId": "8fdc0efb-6fc7-427d-a3a1-fd8cf5eabc20",
  "controller": "admin",
  "action": "truncateCollection",
  "collection": "name of the truncated collection",
  "index": "name of the index containing the truncated collection",
  "volatile": {},
  "state": "done",
  "result": { "acknowledged": true }
}
```