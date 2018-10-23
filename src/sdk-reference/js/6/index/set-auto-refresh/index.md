---
layout: sdk.html.hbs
algolia: true
title: setAutoRefresh
description: Set the autorefresh flag
order: 1000
---

# setAutoRefresh(index, autorefresh, [options])

The setAutoRefresh action allows to set the autorefresh flag for the index.

Each index has an autorefresh flag.  
When set to true, each write request trigger a [refresh](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-refresh.html) action on Elasticsearch.  
Without a refresh after a write request, the documents may not be immediately visible in search.

<div class="alert alert-info">
A refresh operation comes with some performance costs.  
While forcing the autoRefresh can be convenient on a development or test environment,  
we recommend that you avoid using it in production or at least carefully monitor its implications before using it.
</div>

## Signature

```javascript
/**
 * @param {string} index
 * @param {boolean} autoRefresh
 * @param {object} [options]
 * @returns {Promise.<Object>}
 */
setAutoRefresh(index, autoRefresh, (options = null));
```

## Arguments

| Arguments     | Type    | Description                        | |
| ------------- | ------- | ---------------------------------- | -------- |
| `index`       | String  | Index name                         | yes      |
| `autoRefresh` | Boolean | autoRefresh flag                   | yes      |
| `options`     | Object  | Query options | no       |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolve to an object containing the new value of autorefresh flag.

| Name     | Type    | Description                                     |
| -------- | ------- | ----------------------------------------------- |
| response | boolean | new value for the autorefresh flag of the index |

## Usage

[snippet=setAutoRefresh]
