---
layout: sdk.html.hbs
title: setAutoRefresh
description: Set the autorefresh flag
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

```cpp
void setAutoRefresh(const std::string& index, bool autoRefresh, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments     | Type          | Description                                             | Required |
| ------------- | ------------- | ------------------------------------------------------- | -------- |
| `index`       | const std::string&   | Index name                                              | yes      |
| `autoRefresh` | bool       | autoRefresh flag                                        | yes      |
| `options`     | kuzzleio::query_options* | A pointer to a `kuzzleio::query_options` containing query options | no       |

### options

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=setAutoRefresh]
