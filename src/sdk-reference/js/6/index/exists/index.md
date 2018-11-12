---
layout: sdk.html.hbs
algolia: true
title: exists
description: Test if an index exists
order: 300
---

# exists

Checks if the given `<index>` exists in Kuzzle.

## Arguments

```javascript
exists (index, [options]);
```

<br/>

| Arguments | Type   | Description    |
| --------- | ------ | ---------------|
| `index`   | <pre>string</pre> | Index name     |
| `options` | <pre>object</pre> | Query options  |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolve

Resolves to a `boolean` that indicate whether the index exists or not.

## Usage

[snippet=exists]
