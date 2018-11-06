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

## Arguments

```javascript
mCreate (index, collection, documents, [options])
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>string</pre> | A JSON string containing the documents to create |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolve

Resolves to an object containing the created documents.

## Usage

[snippet=m-create]
