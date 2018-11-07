---
layout: sdk.html.hbs
algolia: true
title: mReplace
description:
order: 200
---

# MReplace

Replaces multiple documents.

Returns a partial error (error code 206) if one or more documents can not be replaced.

## Arguments

```go
MReplace(
    index string, 
    collection string, 
    documents json.RawMessage, 
    options types.QueryOptions
) (json.RawMessage, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `documents` | <pre>json.RawMessage</pre> | A JSON string containing the documents to update |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns a JSON string containing the updated documents.

## Usage

[snippet=m-replace]
