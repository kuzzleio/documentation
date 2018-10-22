---
layout: sdk.html.hbs
algolia: true
title: delete
description: Deletes an index
order: 500
---

# Delete

Deletes an entire data index from Kuzzle.

## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<object>}
 */
delete (index, (options = null));
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `index`   | string | Index name                          | yes      |
| `options` | object | Query options. | no       |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an object containing the index deletion status.

| Name         | Type    | Description                                                                 |
| ------------ | ------- | --------------------------------------------------------------------------- |
| acknowledged | boolean | indicates whether the index was successfully deleted in the Elastic cluster |

## Usage

[snippet=delete]
