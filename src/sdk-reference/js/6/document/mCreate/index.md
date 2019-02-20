---
layout: sdk.html.hbs
title: mCreate
description: Create multiple documents in kuzzle
---

# mCreate

Creates multiple documents.

Throws a partial error (error code 206) if one or more documents creations fail.

<br/>

```javascript
mCreate (index, collection, documents, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `documents` | <pre>array<object></pre> | Array of documents to create |
| `options` | <pre>object</pre> | Query options |

### Options

Additional query options

| Options | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Resolves

Resolves to an object containing created documents.

| Property | Type | Description |
| --- | --- | --- |
| `hits` | <pre>array<object></pre> | Created documents |
| `total` | <pre>number</pre> | Total number of created documents |

## Usage

[snippet=m-create]
