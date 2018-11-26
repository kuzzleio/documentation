---
layout: sdk.html.hbs
algolia: true
title: mGet
description: Get multiple documents
algolia: true
---

# mGet

Gets multiple documents.

Throws a partial error (error code 206) if one or more document can not be retrieved.

## Arguments

```javascript
mGet (index, collection, ids, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `ids` | <pre>array<string></pre> | Document ids |
| `options` | <pre>object</pre> | Query options |

### Options

Additional query options

| Options | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

Resolves to an array of retrieved documents.

## Usage

[snippet=m-get]
