---
layout: sdk.html.hbs
title: setAutoRefresh
description: Set the autorefresh flag
---

# setAutoRefresh

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

```csharp
public void setAutoRefresh(string index, bool auto_refresh);

public void setAutoRefresh(
    string index, 
    bool auto_refresh, 
    query_options options);

```

## Arguments

| Arguments     | Type          | Description          |
| ------------- | ------------- | -------------------- |
| `index`       | <pre>string</pre>   | Index name    |
| `autoRefresh` | <pre>bool</pre>       | Autorefresh flag value  |
| `options`     | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |   
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=setAutoRefresh]
