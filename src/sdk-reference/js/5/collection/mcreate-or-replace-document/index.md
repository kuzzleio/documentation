---
layout: sdk.html.hbs
algolia: true
title: mcreateOrReplaceDocument
description: Collection:mcreateOrReplaceDocument
---

  

# mCreateOrReplaceDocument
Create or replace the input [Documents]({{ site_base_path }}sdk-reference/document/).


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns a `JSON object` containing the raw Kuzzle response.
Can return a 206 partial error in cases where some documents could not be created or replaced.

## Usage

[snippet=mcreate-or-replace-document-1]
> Callback response:

```json
{
  "hits": [
    {"first": "document"},
    {"second": "document"}
  ],
  "total": 2
}
```