---
layout: sdk.html.hbs
algolia: true
title: mDelete
description:
order: 200
---

# mDelete

Deletes documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be deleted.

The optional parameter `refresh` can be used
with the value `wait_for` in order to wait for the document indexation (indexed documents are available for `search`).

## Arguments

```go
MDelete(
    index string, 
    collection string, 
    ids []string, 
    options types.QueryOptions
) ([]string, error)
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `ids` | []string | The ids of the documents to delete |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Option | Type (default) | Description |
| --- | --- | --- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns an array of strings containing the ids of the deleted documents.

## Usage

[snippet=m-delete]
