---
layout: sdk.html.hbs
algolia: true
title: setAutoRefresh
description: Set the autorefresh flag
algolia: true
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

```go
SetAutoRefresh(index string, autoRefresh bool, options types.QueryOptions) error
```

## Arguments

| Arguments     | Type         | Description                           | Required |
| ------------- | ------------ | ------------------------------------- | -------- |
| `index`       | string       | Index name                            | yes      |
| `autoRefresh` | Boolean      | autoRefresh flag                      | yes      |
| `options`     | QueryOptions | Query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | bool | Make this request queuable or not | `true`  |

## Return

Return an error or `nil`.

## Usage

[snippet=setAutoRefresh]
