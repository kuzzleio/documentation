---
layout: sdk.html.hbs
algolia: true
title: mdeleteDocument
description: Collection:mdeleteDocument
---

  

# mDeleteDocument
Delete multiple [Documents]({{ site_base_path }}sdk-reference/document/) according to the input IDs.


## Options

| Option | Type | Description | Default |
|
## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.
Can return a 206 partial error in cases where some documents could not be deleted.

## Usage

[snippet=mdelete-document-1]
> Callback response:

```json
["doc1", "doc2"]
```