---
layout: sdk.html.hbs
algolia: true
title: mCreateOrReplace
description:
order: 200
---

# MCreateOrReplace

Creates or replaces multiple documents.

Returns a partial error (error code 206) if one or more document creations/replacements fail.

## Arguments

```go
MCreateOrReplace(
    index string, 
    collection string, 
    body json.RawMessage, 
    options types.QueryOptions
) (json.RawMessage, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the documents to create |
| `options` | <pre>types.QueryOptions</pre> | A struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an JSON string containing the created documents.

## Usage

[snippet=m-create-or-replace]
