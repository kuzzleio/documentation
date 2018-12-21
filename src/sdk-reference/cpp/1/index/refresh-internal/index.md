---
layout: sdk.html.hbs
title: refreshInternal
description: Force refresh of Kuzzle internal index
---

# RefreshInternal

When writing or deleting security and internal documents (users, roles, profiles, configuration, etc.) in Kuzzle, the update needs to be indexed before being reflected in the search index.

The `refreshInternal` action forces a [refresh]({{ ../site_base_path }}/sdk-reference/index/refresh), on the internal index, making the documents available to search immediately.

<div class="alert alert-info">
  A refresh operation comes with some performance costs.

  From [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-refresh.html):
  "While a refresh is much lighter than a commit, it still has a performance cost. A manual refresh can be useful when writing tests, but don’t do a manual refresh every time you index a document in production; it will hurt your performance. Instead, your application needs to be aware of the near real-time nature of Elasticsearch and make allowances for it."
</div>

## Signature

```cpp
void refreshInternal(const std::string& index, kuzzleio::query_options *options = null)
```

## Arguments

| Arguments | Type          | Description                                             | Required |
| --------- | ------------- | ------------------------------------------------------- | -------- |
| `index`   | const std::string&   | Index name                                              | yes      |
| `options` | kuzzleio::query_options* | Query options | no       |

### options

Additional query options

| Option     | Type    | Description                       | Default |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/cpp/1/essentials/error-handling).

## Usage

[snippet=refreshInternal]
