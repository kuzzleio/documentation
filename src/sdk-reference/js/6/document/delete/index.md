---
layout: sdk.html
algolia: true
title: delete
description:
order: 200
---

# delete

Given a document id, deletes the corresponding document from Kuzzle.

Only documents in the persistent data storage layer can be deleted.

The optional parameter refresh can be used with the value wait_for in order to wait for the document to be deleted (and to no longer be available in search).

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {id} id
 * @param {object} options
 * @returns {Promise.<string>}
 */
delete(index, collection, id, (options = null))
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | String | Index name | yes |
| `collection` | String | Collection name | yes |
| `id` | String | Optional document id | no |
| `options` | Object | An object containing query options. | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

Resolves to the id of the deleted document.

## Usage

[snippet=delete]
