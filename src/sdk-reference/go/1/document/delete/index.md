---
layout: sdk.html.hbs
algolia: true
title: delete
description:
order: 200
---

# delete

Given a document id, deletes the corresponding document from Kuzzle.

Only documents in the persistent data storage layer can be deleted.

The optional parameter refresh can be used with the value wait_for in order to wait for the document to be deleted (and to no longer be available in search).

## Arguments

```go
Delete(
  index string, 
  collection string, 
  _id string, 
  options types.QueryOptions
) (string, error)
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `options` | <pre>types.QueryOptions</pre> | An struct containing query options |

### options

Additional query options

| Property   | Type    | Description                       |
| ---------- | ------- | --------------------------------- |
| `Queuable` | <pre>bool</pre>  (`true`) | Make this request queuable or not |
| `Refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns the id of the deleted document.

## Usage

[snippet=delete]
