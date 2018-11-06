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


## Arguments

```go
MGet(
    index string, 
    collection string, 
    ids []string, 
    options types.QueryOptions
) (json.RawMessage, error)
```

| Arguments | Type | Description|
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `ids` | []string | The document ids |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |

## Return

Returns a `json.RawMessage` containing the retrieved documents.

## Usage

[snippet=m-get]
