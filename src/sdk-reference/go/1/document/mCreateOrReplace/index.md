---
layout: sdk.html.hbs
algolia: true
title: mCreateOrReplace
description:
order: 200
---

# mCreateOrReplace

Creates or replaces new documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents fail to create.

## Arguments

```go
MCreateOrReplace(
    index string, 
    collection string, 
    body json.RawMessage, 
    options types.QueryOptions
) (json.RawMessage, error)
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>json.RawMessage</pre> | A JSON string containing the documents to create |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an JSON string containing the created documents.

## Usage

[snippet=m-create-or-replace]
