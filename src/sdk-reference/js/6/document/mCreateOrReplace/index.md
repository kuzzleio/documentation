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

```javascript
mCreateOrReplace (index, collection, documents, options = {})
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>string</pre> | A JSON string containing the documents to create |
| `options` | Object | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolve

Resolves to an object containing the created documents.

## Usage

[snippet=m-create-or-replace]
