---
layout: sdk.html.hbs
algolia: true
title: delete
description: Deletes an index
order: 500
---

# delete

Deletes an entire data `<index>` from Kuzzle.

## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise<object>}
 */
delete (index, options = null)
```

## Arguments

| Arguments | Type   | Description      |
| --------- | ------ | ---------------- |
| `index`   | string | Index name       |
| `options` | object | Query options |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to a `boolean` indicating whether the index was successfully deleted in the Elastic cluster.

## Usage

[snippet=delete]
