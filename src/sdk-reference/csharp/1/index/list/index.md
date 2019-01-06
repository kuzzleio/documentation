---
layout: sdk.html.hbs
title: list
description: List the indexes
---

# list

Get the complete list of data indexes handled by Kuzzle.

## Signature

```csharp
public SWIGTYPE_p_std__vectorT_std__string_t list();
public SWIGTYPE_p_std__vectorT_std__string_t list(QueryOptions options);
```

## Arguments

| Arguments | Type          | Description       |
| --------- | ------------- | ------------------|
| `options` | <pre>Kuzzleio::QueryOptions\*</pre> | Query options |

### options

Additional query options

| Option     | Type<br/>(default)   | Description  |
| ---------- | ------- | --------------------------------- | 
| `queuable` | <pre>bool</pre><br/>(`true`) | If true, queues the request during downtime, until connected to Kuzzle again |

## Return

A vector of string containing the list of indexes present in Kuzzle

## Exceptions

Throws a `Kuzzleio::KuzzleException` if there is an error. See how to [handle error]({{ site_base_path }}sdk-reference/csharp/1/error-handling).

## Usage

[snippet=list]
