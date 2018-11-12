---
layout: sdk.html.hbs
algolia: true
title: getMapping
description: Return collection mapping
---

# getMapping

Returns the mapping for the given `<collection>`.

## Arguments

```javascript
getMapping (index, collection, [options])
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

## Resolve

Resolve to an object representing the collection mapping.

## Usage

[snippet=get-mapping]
