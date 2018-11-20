---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description: Creates or replaces a document
---


# createOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```javascript
createOrReplace (index, collection, id, document, [options])
```

| Argument | Type | Description |
| | _id | <pre>string</pre> | The id of the newly created document
| _version | <pre>number</pre> | The version of the document in the persistent data storage
| _source | <pre>object</pre> | The created document

## Usage

[snippet=create-or-replace]
