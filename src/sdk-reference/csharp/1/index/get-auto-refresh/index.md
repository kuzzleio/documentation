---
layout: sdk.html.hbs
title: getAutoRefresh
description: Returns the status of autorefresh flag
---

# getAutoRefresh

The getAutoRefresh action returns the current autorefresh status for the index.

Each index has an autorefresh flag.
When set to true, each write request trigger a [refresh](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/docs-refresh.html) action on Elasticsearch.
Without a refresh after a write request, the documents may not be immediately visible in search.

<div class="alert alert-info">
  A refresh operation comes with some performance costs.
  While forcing the autoRefresh can be convenient on a development or test environment,
  we recommend that you avoid using it in production or at least carefully monitor its implications before using it.
</div>

## Signature

```csharp
public bool getAutoRefresh(string index);

public bool getAutoRefresh(string index, query_options options);

```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `index`   | <pre>string</pre>   | Index name     |
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |   
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A bool indicating the status of the **autoRefresh** flag.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=getAutoRefresh]
