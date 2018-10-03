---
layout: sdk.html
algolia: true
title: mCreateOrReplace
description:
order: 200
---

# mCreateOrReplace

Creates or replaces new documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents fail to create.

## Signature

```go
MCreateOrReplace(index string, collection string, body json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | string | Index name | yes |
| `collection` | string | Collection name | yes |
| `body` | string | A JSON string containing the documents to create | yes |
| `options` | types.QueryOptions | The query options | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns an JSON string containing the created documents.

## Usage

[snippet=m-create-or-replace]
