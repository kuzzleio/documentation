---
layout: sdk.html.hbs
algolia: true
title: mDelete
description: Delete multiple indexes
order: 600
---

# mDelete

Deletes multiple `<indexes>` at once.

## Arguments

```javascript
mDelete (indexes, options = null);
```

<br/>

| Arguments | Type   | Description                                  |
| --------- | ------ | -------------------------------------------- |
| `indexes` | Array  | An array of strings containing indexes names |
| `options` | <pre>object</pre> | Query options          |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

Resolves to an `array<string>` containing the list of indexes names successfully deleted.

## Usage

[snippet=mDelete]
