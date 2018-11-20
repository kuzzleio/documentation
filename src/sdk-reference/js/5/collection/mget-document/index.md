---
layout: sdk.html.hbs
algolia: true
title: mgetDocument
description: Collection:mgetDocument
---

  

# mGetDocument
Get multiple [Documents]({{ site_base_path }}sdk-reference/document/) according to the input document IDs.


## Options

| Option | Type | Description | Default |
|
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