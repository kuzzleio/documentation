---
layout: sdk.html.hbs
algolia: true
title: validate
description: Validate a document
algolia: true
---

# validate

Validates data against existing validation rules.

Note that if no validation specifications are set for the `<index>`/`<collection>`, the document will always be valid.

This request does **not** store or publish the document.

## Arguments

```javascript
validate (index, collection, document, [options])
```

| Argument | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `document` | <pre>object</pre> | Document to validate |
| `options` | <pre>object</pre> | Query options |

### Options

Additional query options

| Options | Type<br/>(default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Resolves

Resolves to a boolean value set to true if the document is valid and false otherwise.

## Usage

[snippet=validate]
