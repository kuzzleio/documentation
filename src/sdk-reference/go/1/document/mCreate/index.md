---
layout: sdk.html.hbs
algolia: true
title: mCreate
description:
order: 200
---

# mCreate

Creates new documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents fail to create.


## Signature

```go
MCreate(index string, collection string, body json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | string | A JSON string containing the documents to create |
| `options` | types.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `Refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns an JSON string containing the created documents.

## Usage

[snippet=m-create]
