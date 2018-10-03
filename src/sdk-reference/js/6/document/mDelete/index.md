---
layout: sdk.html
algolia: true
title: mDelete
description:
order: 200
---

# mDelete

Deletes documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more document can not be deleted.

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {Array.<string>} ids
 * @param {object} options
 * @returns {Promise.<Array.<string>>}
 */
mDelete (index, collection, ids, options = {})
```

## Arguments

| Arguments | Type | Description | Required |
| --- | --- | --- | --- |
| `index` | string | Index name | yes |
| `collection` | string | Collection name | yes |
| `ids` | Array | The ids of the documents to delete | no |
| `options` | object | An object containing query options. | no |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |
| `refresh` | string | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) | `` |

## Resolve

## Usage

[snippet=m-delete]
