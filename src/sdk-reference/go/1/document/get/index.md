---
layout: sdk.html.hbs
algolia: true
title: get
description:
order: 200
---

# get

Given a document id, retrieves the corresponding document from the database.

Only documents in the persistent data storage layer can be retrieved.


## Arguments

```go
Get(
  index string, 
  collection string, 
  _id string, 
  options types.QueryOptions
) (json.RawMessage, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a JSON string containing the document

| Name | Type | Description
| --- | --- | ---
| _source | object | The retrieved document

## Usage

[snippet=get]
