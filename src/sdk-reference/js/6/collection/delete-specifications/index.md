---
layout: sdk.html.hbs
algolia: true
title: deleteSpecifications
description: Delete validation specifications for a collection
---

# deleteSpecifications

Delete the validation specifications associated with the collection.

## Arguments

```javascript
deleteSpecifications (index, collection, [options])
```

<br/>

| Arguments    | Type    | Description |
|--------------|---------|-------------|
| ``index`` | <pre>string</pre> | Index name    |
| ``collection`` | <pre>string</pre> | Collection name    |
| ``options`` | <pre>object</pre> | Query options    |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

Resolves if success.

## Usage

[snippet=delete-specifications]
