---
layout: sdk.html.hbs
algolia: true
title: mGet
description:
order: 200
---

# mGet

Given `document ids`, retrieves the corresponding documents from the database.

Only documents in the persistent data storage layer can be retrieved.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be retrieved.

## Signature

```go
MGet(index string, collection string, ids []string, includeTrash bool, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description|
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `ids` | []string | The document ids |
| `options` | types.QueryOptions | The query options |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `Queuable` | boolean | Make this request queuable or not | `true`  |
| `IncludeTrash` | boolean | If set to `true`, includes the documents from the trash | `false`  |

## Return

Returns a `json.RawMessage` containing the retrieved documents.

## Usage

[snippet=m-get]
