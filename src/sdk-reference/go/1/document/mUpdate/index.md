---
layout: sdk.html
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

## Signature

```go
MUpdate(index string, collection string, documents json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `documents` | json.RawMessage | A JSON string containing the documents to update |
| `options` | types.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `Refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |
| `RetryOnConflict` | int | The number of times the database layer should retry in case of version conflict | 0 |

## Return

Returns a JSON string containing the update documetns.

## Usage

[snippet=m-update]
