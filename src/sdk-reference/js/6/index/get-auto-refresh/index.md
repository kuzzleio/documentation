---
layout: sdk.html.hbs
algolia: true
title: getAutoRefresh
description: Returns the status of autorefresh flag
order: 900
---

# GetAutoRefresh

The getAutoRefresh action returns the current autorefresh status for the index.

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
 * @param {object} [options]
 * @returns {Promise.<Boolean>}
 */
getAutoRefresh(index, (options = null));
```

## Arguments

| Arguments | Type   | Description                         | Required |
| --------- | ------ | ----------------------------------- | -------- |
| `index`   | string | Index name                          | yes      |
| `options` | object | Query options. | no       |

### **options**

Additional query options

| Property   | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Resolve

Resolves to a `boolean` that indicate the status of the **autoRefresh** flag.

## Usage

[snippet=getAutoRefresh]
