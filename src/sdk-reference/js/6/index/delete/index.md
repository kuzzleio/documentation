---
layout: sdk.html.hbs
algolia: true
title: delete
description: Deletes an index
---

# delete

Deletes an entire data `<index>` from Kuzzle.

## Arguments

```javascript
delete (index, [options])
```

<br/>

| Arguments | Type   | Description      |
| --------- | ------ | ---------------- |
| `index`   | <pre>string</pre> | Index name       |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

Resolves to a `true` if index was successfully deleted from ElasticSearch cluster, `false`otherwise.

## Usage

[snippet=delete]
