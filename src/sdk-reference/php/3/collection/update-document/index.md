---
layout: sdk.html.hbs
algolia: true
title: updateDocument
description: Collection:updateDocument
---

  

# updateDocument
Update parts of a document, by replacing some fields or adding new ones.  
Note that you cannot remove fields this way: missing fields will simply be left unchanged.


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns an up-to-date [Document]({{ site_base_path }}sdk-reference/document/) object.

## Usage

[snippet=update-document-1]
