---
layout: sdk.html.hbs
algolia: true
title: update
description:
order: 200
---

# Update

Update a document in Kuzzle.

Only documents in the persistent data storage layer can be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Signature

```go
Update(index string, collection string, id string, body json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `id` | string | The document id |
| `body` | string | A JSON string containing the body of the document |
| `options` | types.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |
| `retryOnConflict` | int | The number of times the database layer should retry in case of version conflict | 0 |

## Return

Returns a JSON string containing the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | string | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| result | string | set to `updated` in case of success

## Usage

[snippet=update]
