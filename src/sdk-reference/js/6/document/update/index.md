---
layout: sdk.html.hbs
algolia: true
title: update
description:
order: 200
---

# update

Update a document in Kuzzle.

Only documents in the persistent data storage layer can be updated.

Conflicts may occur if the same document gets updated multiple times within a short time on a database cluster. When this happens, Kuzzle answers with an error that clients have to handle.  
You may set the `retryOnConflict` optional argument with a positive integer, asking Kuzzle to retry updating the document that number of times before rejecting the request with an error.

## Arguments

```javascript
update (index, collection, id, body, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | The document id |
| `body` | <pre>object</pre> | The partial content of the document to update |
| `options` | <pre>object</pre> | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |
| `refresh` | <pre>string</pre> | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |
| `retryOnConflict` | <pre>int</pre> (`0`) | The number of times the database layer should retry in case of version conflict |

## Resolve

Resolves to an object containing the the document update result.

| Name | Type | Description
| --- | --- | ---
| _id | <pre>string</pre> | The id of the newly created document
| _version | int | The version of the document in the persistent data storage
| result | <pre>string</pre> | set to `updated` in case of success

## Usage

[snippet=update]
