---
layout: sdk.html
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

```java
void setAutoRefresh(String index, boolean autoRefresh, QueryOptions options)
void setAutoRefresh(String index, boolean autoRefresh)
```

## Arguments

| Arguments     | Type         | Description       | Required |
| ------------- | ------------ | ----------------- | -------- |
| `index`       | String       | Index name        | yes      |
| `autoRefresh` | Boolean      | autoRefresh flag  | yes      |
| `options`     | QueryOptions | The query options | no       |

### **Options**

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | ------- |
| `queuable` | boolean | Make this request queuable or not | `true`  |

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/essentials/error-handling).

## Usage

[snippet=setAutoRefresh]
