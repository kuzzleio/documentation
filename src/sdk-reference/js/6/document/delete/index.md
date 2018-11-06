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

```javascript
delete(index, collection, id, [options])
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | String | Index name |
| `collection` | String | Collection name |
| `id` | String | Optional document id |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolve

Resolves to the id of the deleted document.

## Usage

[snippet=delete]
