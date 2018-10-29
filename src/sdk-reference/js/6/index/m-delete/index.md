---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Deletes multiple indexes
order: 600
---

# mDelete

Deletes multiple `<indexes>` at once.

## Signature

```javascript
/**
 * @param {array<string>} indexes
 * @param {object} [options]
 * @returns {Promise<array<string>>}
 */
mDelete (indexes, options = null);
```

## Arguments

| Arguments | Type   | Description                                  |
| --------- | ------ | -------------------------------------------- |
| `indexes` | Array  | An array of strings containing indexes names |
| `options` | object | Query options          |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `array<string>` containing the list of indexes names successfully deleted.

## Usage

[snippet=mDelete]
