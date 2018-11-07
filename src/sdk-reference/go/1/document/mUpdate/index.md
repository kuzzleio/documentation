---
layout: sdk.html.hbs
algolia: true
title: mUpdate
description:
order: 200
---

# mUpdate

Updates documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents can not be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Arguments

```go
MUpdate(
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
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `RetryOnConflict` | int(`0`) | The number of times the database layer should retry in case of version conflict |

## Return

Returns a JSON string containing the update documetns.

## Usage

[snippet=m-update]
