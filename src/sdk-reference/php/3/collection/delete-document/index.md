---
layout: sdk.html.hbs
algolia: true
title: deleteDocument
description: Collection:deleteDocument
---

  

# deleteDocument
Delete a stored document, or all stored documents matching a search filter.

<aside class="notice">
There is a small delay between the time a document is deleted and it being reflected in the search layer (usually a couple of seconds). That means that a document that was just deleted may still be returned by this function at first.
</aside>


## deleteDocument(filters, [options], [callback])

| Arguments | Type | Description |
|
## Options

| Option | Type | Description | Default |
|
## Return Value

Returns the `Collection` object to allow chaining.

---

## Callback Response

Returns an `array` containing the ids of the deleted documents.

## Usage

[snippet=delete-document-1]
> Callback response:

```json
[ "AVCoeBkimsySTKTfa8AX" ]
```