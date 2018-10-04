---
layout: sdk.html
algolia: true
title: get
description:
order: 200
---

# get

Given a document id, retrieves the corresponding document from the database.

Only documents in the persistent data storage layer can be retrieved.

## Signature

```go
Get(index string, collection string, _id string, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description | 
| --- | --- | --- |
| `index` | string | Index name | 
| `collection` | string | Collection name |
| `id` | string | The document id |
| `options` | types.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |

## Return

Returns a JSON string containing the document

| Name | Type | Description
| --- | --- | ---
| _source | object | The retrieved document

## Usage

[snippet=get]
