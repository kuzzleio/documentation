---
layout: sdk.html
algolia: true
title: delete
description:
order: 200
---

# delete

Given a document id, deletes the corresponding document from Kuzzle.

Only documents in the persistent data storage layer can be deleted.

The optional parameter refresh can be used with the value wait_for in order to wait for the document to be deleted (and to no longer be available in search).

## Signature

```go
Delete(index string, collection string, _id string, options types.QueryOptions) (string, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `id` | string | The document id |
| `options` | types.QueryOptions | A pointer to a `query_options` containing query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `Refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns the id of the deleted document.

## Usage

[snippet=delete]
