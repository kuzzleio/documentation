---
layout: sdk.html.hbs
algolia: true
title: create
description: Create a new document
---


# Create

Creates a new document in the persistent data storage.

Returns an error if the document already exists.

The optional parameter `refresh` can be used with the value `wait_for` in order to wait for the document to be indexed (indexed documents are available for `search`).

## Arguments

```go
Create(
  index string,
  collection string,
  id string,
  document json.RawMessage,
  options types.QueryOptions) (json.RawMessage, error) {
```

<br/>

| Argument | Type | Description |
| | _id | string | Newly created document ID
| _version | int | Version of the document in the persistent data storage
| _source | object | The created document
| result | <pre>string</pre> | Set to `created` in case of success and `updated` if the document already exists

## Usage

[snippet=create]
