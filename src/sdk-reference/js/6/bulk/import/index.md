---
layout: sdk.html.hbs
algolia: true
title: import
description: Performs a bulk import on a collection
---


# Import


Create, update or delete large amount of documents as fast as possible.

This route is faster than the `document:m*` routes family (e.g. [document:mCreate]({{ site_base_path }}sdk-reference/js/6/document/m-create)), but no real-time notifications will be generated, even if some of the documents in the import match subscription filters.

If some documents actions fail, the client will receive a [PartialError]({{ site_base_path }}api/1/documentation/errors/#partialerror) error.

## Arguments

```javascript
import (bulkData, [options])
```

<br/>

| Arguments  | Type        | Description                                         |
| 
## Resolves

An object containing information about the import status for each document.

| Property     | Type  | Description   |
| -------------- | --------- | ------------- |
|  `errors`  |  <pre>boolean</pre> |  `true` if there is some errors with the import |
|  `items`  |  <pre>object[]</pre> |  Array of object containing document import statuses |

Each object has the following structure:

```javascript
{
  "<action>": {
    _id: "another-id",
    status: 200
  }
}
```

## Usage

[snippet=import]
