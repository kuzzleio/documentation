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

## Signature

```javascript
/**
 * @param {string} index
 * @param {string} collection
 * @param {object} body
 * @param {object} options
 * @returns {Promise.<boolean>}
 */
validate (index, collection, body, options = {})
```

## Arguments

| Arguments | Type | Description |
| --- | --- | --- |
| `index` | string | Index name |
| `collection` | string | Collection name |
| `body` | object | The body of the document to validate |
| `options` | object | An object containing query options. |

### Options

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to a boolean value set to true if the document is valid and false otherwise.

## Usage

[snippet=validate]
