---
layout: sdk.html.hbs
algolia: true
title: get
description: Get a document from kuzzle
---

# get

Gets a document.

## Arguments

```javascript
get (index, collection, id, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `id` | <pre>string</pre> | Document ID  |
| `options` | <pre>object</pre> | An object containing query options. |

### Options

Additional query options

| Options | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

## Usage

[snippet=get]
