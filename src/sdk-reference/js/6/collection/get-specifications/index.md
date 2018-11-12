---
layout: sdk.html.hbs
algolia: true
title: getSpecifications
description: Returns the validation specifications
---

# getSpecifications

Returns the validation specifications associated to the `<collection>`.

## Arguments

```javascript
getSpecifications (index, collection, options = null)
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|----------
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``options`` | <pre>object</pre> | Query options    |

### **options**

Additional query options

| Property     | Type    | Description   |
| -------------- | --------- | ------------- |
| `queuable` | <pre>boolean</pre> | Make this request queuable or not | `true`  |

## Resolve

Resolve to an object representing the collection specifications.

## Usage

[snippet=get-specifications]
