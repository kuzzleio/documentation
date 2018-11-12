---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Deletes multiple indexes
---

# mDelete

Deletes multiple indexes at once.

## Signature

```javascript
/**
 * @param {array} indexes
 * @param {object} [options]
 * @returns {Promise.<Array>}
 */
mDelete(indexes, options = null);
```

## Arguments

| Arguments | Type   | Description                                  | Required |
| --------- | ------ | -------------------------------------------- | -------- |
| `indexes` | Array  | An array of strings containing indexes names | yes      |
| `options` | Object | An object containing query options.          | no       |

### **Options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `Array` of strings containing the list of indexes names successfully deleted.

## Usage

[snippet=mDelete]
