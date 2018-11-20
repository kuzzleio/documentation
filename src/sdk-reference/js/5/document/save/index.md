---
layout: sdk.html.hbs
algolia: true
title: save
description: Document:save
---

  

# save
Saves this document into Kuzzle.

If this is a new document, this function will create it in Kuzzle and the ``id`` property will be made available.  
Otherwise, this method will replace the latest version of the document in Kuzzle with the content of this current object.


## Options

| Option | Type | Description | Default |
|
## Return Value

Returns this `Document` object to allow chaining.

---

## Callback Response

Return this `Document` object once the document has been saved.

## Usage

[snippet=save-1]
