---
layout: sdk.html.hbs
title: deleteByQuery
description: Delete documents matching query
order: 200
---

# deleteByQuery

Deletes documents matching the provided search query.

Kuzzle uses the [ElasticSearch Query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl.html) syntax.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t deleteByQuery(string index, string collection, string body);
public SWIGTYPE_p_std__vectorT_std__string_t deleteByQuery(string index, string collection, string body, QueryOptions options);
```

### options

Additional query options

| Options   | Type<br/>(default) | Description                       |
| ---------- | ------- | --------------------------------- |
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again  |
| `refresh` | <pre>string</pre><br/>(`""`) | If set to `wait_for`, waits for the change to be reflected for `search` (up to 1s) |

## Return

Returns the list of the deleted document ids.

## Exceptions

Throws a `KuzzleException` if there is an error. See how to [handle errors]({{ site_base_path }}sdk-reference/csharp/1/essentials/error-handling).

## Usage

[snippet=delete-by-query]
