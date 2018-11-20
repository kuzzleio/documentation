---
layout: sdk.html.hbs
algolia: true
title: createOrReplace
description: Creates or replaces a document
---


# CreateOrReplace

Creates a new document in the persistent data storage, or replaces its content if it already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```go
CreateOrReplace(
  index string,
  collection string,
  _id string,
  document json.RawMessage,
  options types.QueryOptions) (json.RawMessage, error)
```

<br/>

| Argument | Type | Description |
| | _id | string | Newly created document ID
| _version | int | Version of the document in the persistent data storage
| _source | object | The created document
| result | <pre>string</pre> | Set to `created` in case of success and `updated` if the document already exists

## Usage

[snippet=create-or-replace]
