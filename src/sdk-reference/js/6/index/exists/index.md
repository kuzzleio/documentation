---
layout: sdk.html.hbs
algolia: true
title: exists
description: Check for index existence
---

# Exists

Checks if the given index exists in Kuzzle.

## Signature

```javascript
/**
 * @param {string} index
 * @param {object} [options]
 * @returns {Promise.<Boolean>}
 */
exists(index, options = null);
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `index`   | String | Index name                          | yes      |
| `options` | Object | An object containing query options. | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to a `boolean` that indicate whether the index exists or not.

## Usage

[snippet=exists]
