---
layout: sdk.html.hbs
algolia: true
title: mGet
description:
order: 200
---

# MGet

Gets multiple documents.

Returns a partial error (error code 206) if one or more document can not be retrieved.

## Arguments

```go
MGet(
    index string, 
    collection string, 
    ids []string, 
    options types.QueryOptions
) (json.RawMessage, error)
```

| Arguments | Type | Description|
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `ids` | []string | The document ids |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

Returns a `json.RawMessage` containing the retrieved documents.

## Usage

[snippet=m-get]
