---
layout: sdk.html.hbs
algolia: true
title: delete
description: Delete an index
order: 500
---

# delete

Deletes an entire data `<index>` from Kuzzle.

## Arguments

```javascript
delete (index, options = null)
```

<br/>

| Arguments | Type   | Description      |
| --------- | ------ | ---------------- |
| `index`   | <pre>string</pre> | Index name       |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

Resolves to a `boolean` indicating whether the index was successfully deleted in the Elastic cluster.

## Usage

[snippet=delete]
