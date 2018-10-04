---
layout: sdk.html
algolia: true
title: mReplace
description:
order: 200
---

# mReplace

Replaces documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more documents can not be replaced.

## Signature

```go
MReplace(index string, collection string, documents json.RawMessage, options types.QueryOptions) (json.RawMessage, error)
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
| `Refresh` | std::string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Return

Returns a JSON string containing the updated documents.

## Usage

[snippet=m-replace]
