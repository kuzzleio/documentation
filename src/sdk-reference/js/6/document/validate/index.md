---
layout: sdk.html.hbs
algolia: true
title: validate
description:
order: 200
---

# validate

Validates data against existing validation rules. 

Note that if no validation specifications are set for the `<index>`/`<collection>`, the document will always be valid.

This request does **not** store or publish the document.

## Arguments

```javascript
validate (index, collection, body, [options])
```

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | <pre>string</pre> | Index name |
| `collection` | <pre>string</pre> | Collection name |
| `body` | <pre>object</pre> | The body of the document to validate |
| `options` | <pre>object</pre> | An object containing query options. |

### Options

Additional query options

| Options | Type (default) | Description |
| --- | --- | --- |
| `queuable` | <pre>boolean</pre> (`true`) | Make this request queuable or not |

## Resolve

Resolves to a boolean value set to true if the document is valid and false otherwise.

## Usage

[snippet=validate]
