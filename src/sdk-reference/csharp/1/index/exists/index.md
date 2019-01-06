---
layout: sdk.html.hbs
title: exists
description: Check for index existence
---

# exists

Checks if the given index exists in Kuzzle.

## Signature

```csharp
public bool exists(string index);

public bool exists(string index, query_options options);

```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `index`   | <pre>string</pre>   | Index name     |
| `options` | <pre>Kuzzleio::QueryOptions</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |   
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A bool indicating whether the index exists or not.

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=exists]
