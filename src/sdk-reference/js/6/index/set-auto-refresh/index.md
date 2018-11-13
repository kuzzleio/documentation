---
layout: sdk.html.hbs
algolia: true
title: setAutoRefresh
description: Set the autorefresh flag
---

# setAutoRefresh

The setAutoRefresh action allows to set the autorefresh flag for the `<index>`.

Each index has an autorefresh flag.
When set to true, each write request trigger a [refresh](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-refresh.html) action on Elasticsearch.
Without a refresh after a write request, the documents may not be immediately visible in search.

<div class="alert alert-info">
A refresh operation comes with some performance costs.
While forcing the autoRefresh can be convenient on a development or test environment,
we recommend that you avoid using it in production or at least carefully monitor its implications before using it.
</div>

## Arguments

```javascript
setAutoRefresh (index, autoRefresh, [options]);
```

<br/>

| Arguments     | Type    | Description                        |
| ------------- | ------- | ---------------------------------- |
| `index`       | <pre>string</pre> | Index name                         |
| `autoRefresh` | <pre>boolean</pre> | AutoRefresh value                   |
| `options`     | <pre>number</pre> | Query options |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolves

Resolve to a `boolean` representing the new value of the autorefresh flag.

## Usage

[snippet=setAutoRefresh]
