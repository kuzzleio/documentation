---
layout: sdk.html.hbs
algolia: true
title: mReplace
description:
order: 200
---

# mReplace

Replaces documents in the persistent data storage.

Returns a [partial error]({{ site_base_path }}api-documentation/errors/#partialerror) (with status 206) if one or more documents can not be replaced.

## Signature


## Arguments

```javascript
mReplace (index, collection, documents, [options])
```

| Arguments | Type | Description |
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

## Resolve

Resolves to an array of updated documents.

## Usage

[snippet=m-replace]
