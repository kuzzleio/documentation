---
layout: sdk.html.hbs
algolia: true
title: create
description: Create a new document
---


# create

Creates a new document in the persistent data storage.

Throws an error if the document already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```javascript
create (index, collection, id, document, [options])
```

| Argument | Type | Description |
| | _id | <pre>string</pre> | ID of the newly created document
| _version | <pre>number</pre> | Version of the document in the persistent data storage
| _source | <pre>object</pre> | Created document

## Usage

[snippet=create]
