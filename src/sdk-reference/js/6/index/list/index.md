---
layout: sdk.html
algolia: true
title: list
description: List the indexes
order: 400
---

# List

Get the complete list of data indexes handled by Kuzzle.

## Signature

```javascript
/**
 * @param {object} [options]
 * @returns {Promise.<Array>}
 */
list((options = null));
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `options` | Object | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `Array` of strings containing the list of indexes names present in Kuzzle

## Usage

[snippet=list]
