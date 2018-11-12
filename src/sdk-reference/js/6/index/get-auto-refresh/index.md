---
layout: sdk.html.hbs
algolia: true
title: getAutoRefresh
description: Return autorefresh status for index
order: 900
---

# getAutoRefresh

This action returns the current autorefresh status for the `<index>`.

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
getAutoRefresh (index, [options]);
```

<br/>

| Arguments | Type   | Description                         |
| --------- | ------ | ----------------------------------- |
| `index`   | <pre>string</pre> | Index name                          |
| `options` | <pre>object</pre> | Query options |

### **options**

Additional query options

| Property     | Type<br/>(default)    | Description   |
| -------------- | --------- | ------------- |
|  `queuable`  |  <pre>boolean</pre> <br/>(`true`) |  Make this request queuable or not  |

## Resolve

Resolves to a `boolean` that indicate the status of the **autoRefresh** flag.

## Usage

[snippet=getAutoRefresh]
