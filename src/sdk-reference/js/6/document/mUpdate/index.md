---
layout: sdk.html.hbs
algolia: true
title: mUpdate
description:
order: 200
---

# mUpdate

Updates documents in the persistent data storage.

Returns a partial error (with status 206) if one or more documents can not be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Arguments

```javascript
mReplace (index, collection, documents, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `documents` | <pre>string</pre> | A JSON string containing the documents to update |
| `options` | <pre>object</pre> | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre> (`0`) | The number of times the database layer should retry in case of version conflict |

## Resolve

Resolves to an array of updated documents.

## Usage

## Usage

[snippet=m-update]
