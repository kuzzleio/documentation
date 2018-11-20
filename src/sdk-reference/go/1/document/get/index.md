---
layout: sdk.html.hbs
algolia: true
title: get
description: Get a document from kuzzle
---


# Get

Gets a document.

## Arguments

```go
Get(
  index string,
  collection string,
  _id string,
  options types.QueryOptions) (json.RawMessage, error)
```

<br/>

| Argument | Type | Description |
| | _id | string | Newly created document ID
| _version | int | Version of the document in the persistent data storage
| _source | object | The created document

## Usage

[snippet=get]
