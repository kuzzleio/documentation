---
layout: sdk.html.hbs
algolia: true
title: mgetDocument
description: Collection:mgetDocument
algolia: true
---
  

# mGetDocument
Get multiple [Documents]({{ site_base_path }}sdk-reference/document/) according to the input document IDs.

---

## mGetDocument(documentIds, [options], callback)

| Arguments | Type | Description |
|---------------|---------|----------------------------------------|
| ``documentIds`` | String[] | Array of IDs of documents to retrieve |
| ``options`` | JSON Object | Optional parameters |
| ``callback`` | function | Callback handling the response |

---

## Options

| Option | Type | Description | Default |
|---------------|---------|----------------------------------------|---------|
| ``queuable`` | boolean | Make this request queuable or not  | ``true`` |

---

## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.

## Usage

[snippet=mget-document-1]
> Callback response:

```json
{
  "hits": [
    {"_id": "doc1", "first": "document"},
    {"_id": "doc2", "second": "document"}
  ],
  "total": 2
}
```