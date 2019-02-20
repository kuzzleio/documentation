---
layout: sdk.html.hbs
title: mReplace
description: Replace documents
---

# mReplace

Replaces multiple documents.

Throws a partial error (error code 206) if one or more documents can not be replaced.

<br/>

```javascript
mReplace (index, collection, documents, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `documents` | <pre>array<object></pre> | Array of documents to update |
| `options` | <pre>object</pre> | Query options |

### Options

Additional query options

| Options | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolves

Resolves to an object containing the replaced documents.

| Property | Type | Description |
| --- | --- | --- |
| `hits` | <pre>array<object></pre> | Replaced documents |
| `total` | <pre>number</pre> | Total replaced documents |


## Usage

[snippet=m-replace]
